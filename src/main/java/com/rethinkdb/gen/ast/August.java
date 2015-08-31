// Autogenerated by metajava.py.
// Do not edit this file directly.
// The template for this file is located at:
// ../../../../../../../../templates/AstSubclass.java

package com.rethinkdb.gen.ast;

import com.rethinkdb.gen.proto.TermType;
import com.rethinkdb.gen.exc.ReqlDriverError;
import com.rethinkdb.model.Arguments;
import com.rethinkdb.model.OptArgs;
import com.rethinkdb.ast.ReqlAst;



public class August extends ReqlExpr {


    public August(Object arg) {
        this(new Arguments(arg), null);
    }
    public August(Arguments args){
        this(args, null);
    }
    public August(Arguments args, OptArgs optargs) {
        this(TermType.AUGUST, args, optargs);
    }
    protected August(TermType termType, Arguments args, OptArgs optargs){
        super(termType, args, optargs);
    }
}
