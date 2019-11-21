package pset3;

import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.generic.*;

public class GraphGenerator {
    public CFG createCFG(String className) throws ClassNotFoundException {
        CFG cfg = new CFG();
        JavaClass jc = Repository.lookupClass(className);
        ClassGen cg = new ClassGen(jc);
        ConstantPoolGen cpg = cg.getConstantPool();
        for (Method m: cg.getMethods()) {
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();
            for (InstructionHandle ih: handles) {
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();
                // your code goes here
                String name = inst.getName();
                InstructionHandle nextHandle = ih.getNext();
                if (name.contains("return")) {
                    cfg.addEdge(position, -1, m, jc);
                }else if (name.contains("load") || name.contains("invokespecial")) {
                    cfg.addEdge(position, nextHandle.getPosition(), m, jc);
                }else if (name.contains("if")) {
                    BranchInstruction bi = (BranchInstruction) inst;
                    cfg.addEdge(position, bi.getTarget().getPosition(), m, jc);
                    cfg.addEdge(position, nextHandle.getPosition(), m, jc);
                }
            }
        }
        return cfg;
    }

    public CFG createCFGWithMethodInvocation(String className) throws ClassNotFoundException {
        // your code goes here
        CFG cfg = createCFG(className);
        JavaClass jc = Repository.lookupClass(className);
        ClassGen cg = new ClassGen(jc);
        ConstantPoolGen cpg = cg.getConstantPool();
        for (Method m: cg.getMethods()) {
            cfg.addNode(-1, m, jc);
            MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
            InstructionList il = mg.getInstructionList();
            InstructionHandle[] handles = il.getInstructionHandles();
            for (InstructionHandle ih: handles) {
                int position = ih.getPosition();
                cfg.addNode(position, m, jc);
                Instruction inst = ih.getInstruction();
                String name = inst.getName();
                InstructionHandle nextHandle = ih.getNext();
                if (name.contains("return")){
                    cfg.addEdge(-1, m, jc, -1, m, jc);
                }else if (name.contains("load") || name.contains("invokespecial")) {
                    cfg.addEdge(position, nextHandle.getPosition(), m, jc);
                }else if (name.contains("if")) {
                    BranchInstruction bi = (BranchInstruction) inst;
                    cfg.addEdge(position, bi.getTarget().getPosition(), m, jc);
                    cfg.addEdge(position, nextHandle.getPosition(), m, jc);
                }else if (name.toLowerCase().contains("invokestatic")) {
                    InvokeInstruction ii = (InvokeInstruction) inst;
                    JavaClass nextC = Repository.lookupClass(ii.getMethodName(cpg));
                    Method nextM = cg.containsMethod(ii.getName(cpg), ii.getSignature(cpg));
                    cfg.addEdge(position, m, jc, 0, nextM, nextC);
                    cfg.addEdge(-1, nextM, nextC, nextHandle.getPosition(), m, jc);
                }
            }
        }
        return cfg;
    }

    public static void main(String[] a) throws ClassNotFoundException {
        GraphGenerator gg = new GraphGenerator();
        gg.createCFG("pset3.C");
        gg.createCFGWithMethodInvocation("pset3.D");
    }
}
