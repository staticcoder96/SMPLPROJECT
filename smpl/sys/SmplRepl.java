package smpl.sys;

import smpl.semantics.SmplEvaluator;
import java.io.*;
import smpl.syntax.smplLexer;
import smpl.syntax.SMPLParser;
import smpl.syntax.SmplProgram;
import smpl.values.SmplValue;
import java.util.ArrayList;

public class SmplRepl<S, T> {

    public final String PROMPT = "SMPL >";
    public static SmplEvaluator evalClass;

    public static void usage() {
        String[] instructions = {
            "The SmplRepl may be invoked with 0 or more arguments.",
            "If no arguments are given, the Smpl evaluator is used to",
            "interpret commands read from standard input."
        };
        for (String line: instructions) {
            System.out.println(line);
        }
        
    }
    
    //@SuppressWarnings("unchecked")
    public static void main(String args[]) {
        // if provided, first command line argument is class of evaluator
        // default is FractalEvaluator
        int width = 0;
        int height = 0;
        //S-type of the input argument used;T-type of the output returned.
        SmplRepl<S, T> SmplRepl;
        Class<? extends SmplEvaluator> evalClass = SmplEvaluator.class;
        ArrayList<String> fileList = new ArrayList<>();
        if (args.length > 0) {        
            try {                
                int idx = 0;
                while (idx < args.length && !args[idx].equals("--")) {
                    System.out.println(args[idx]);
                    idx += 1;
                }
                if (idx == 0) {
                    evalClass = SmplEvaluator.class;
                } else {
                    evalClass = (Class<? extends SmplEvaluator>) Class.forName(args[idx-1]); //load any given class as string at runtime.
                }                
                for (int i = idx+1; i < args.length; i++) {
                    fileList.add(args[i]);
                }                
            } catch (ClassNotFoundException cnfe) {
                System.err.println(cnfe.getMessage());
                usage();
                System.exit(1);
            }
        }
        SmplRepl = new SmplRepl<>(evalClass, width, height);
        SmplRepl.visitFiles(fileList);
        SmplRepl.loop();
    }

    public void visitFiles(ArrayList<String> fileNames) {
        // Treat all other command line arguments as files to be read and evaluated
        FileReader freader;
        for (String file : fileNames) {
            try {
                System.out.println("Reading from: " + file + "...");
                freader = new FileReader(new File(file));
                evalShow(interp, freader);
                System.out.println("Done! Press ENTER to continue");
                System.in.read();
            } catch (FileNotFoundException fnfe) {
                System.err.println(fnfe.getMessage());
                System.err.println("Skipping it");
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        }
    }

    /**
     * The driver loop in which the standard input is read until EOF is pressed
     * (Ctrl-D on Unix, Ctrl-Z on Windows); on each pass of the loop, that input
     * is parsed, and visited, and the result is displayed .
     */
    public void loop() {
        System.out.println("Type commands at the prompt.\n" + 
                "Press Ctrl-D (Ctrl-Z on Windows) to evaluate them.\n");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            evalShow(interp, reader);
        }
    }

    /**
     * Read a program from the given input reader, then parse it and evaluate it
     * and display the result.
     *
     * @param interp The interpreter to be used to evaluate the program
     * @param reader The input reader supplying the program
     */
    public static void evalShow(SmplEvaluator interp, Reader reader) {
        SmplParser parser;
        SmplProgram program = null;

        System.out.print(PROMPT);
        try {
            parser = new SmplParser(new smplLexer(reader));
            program = (SmplProgram) parser.parse().value;
        } catch (Exception e) {
            System.out.println("Parse Error: " + e.getMessage());
        }

        if (program != null) {
            try {
                 object result;
                // A null state indicates that this is the entry call to interp
                result = program.visit(interp, null);
                System.out.println("\nResult: " + result);
            } catch (SmplException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}