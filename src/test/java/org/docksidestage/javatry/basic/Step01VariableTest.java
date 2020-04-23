/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.javatry.basic;

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author saeyoung kim
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() {
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8nullmai
        // TODO [comment] こちらは print メソッドの document なので、正しくは StringBuilder の document を見るべきです by subaru (2020/04/22)

        // ここで、String1 + String2であれば動作手順として
        // 1 StringBuilderが呼ばれる
        // 2 StringBuilder temp = new StringBuilder(String1)みたいなものが呼ばれる
        // 3 temp = temp.append(String2)になる
        // #ここでString2 == nullであれば、候補としてStringBufferとStringがあるがここではStringと宣伝しているものを使うので
        // 4 temp = temp.append("null")
        // return temp.toString()
        // という理解で大丈夫でしょうか
        // TODO [comment] 概ね理解としてはあっています。 by subaru (2020/04/23)
        // 少し気になるのは 4 で、
        // temp = temp.append(null)
        // だね。null -> "null" になるのはあくまで append メソッドの内部での話なので。
        // + operator がどのような処理をしているかは、この辺りの解説が分かりやすいと思います。
        // https://stackoverflow.com/questions/4648607/stringbuilder-stringbuffer-vs-operator

        // https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
        // + オペレーターを呼び出す時、内部では StringBuilder が呼び出されています。
        // そして StringBuilder の append メソッドの Document には 引数が null だった時の挙動が明記されています。
        /*
            pulbic void print(String s)
            Prints a string. If the argument is null then the string "null" is printed.
            Otherwise, the string's characters are converted into bytes according to the platform's default character encoding,
            and these bytes are written in exactly the manner of the write(int) method.

            From https://docs.oracle.com/javase/6/docs/api/java/io/PrintStream.html#print(java.lang.String)
        */
        /*
            public static String valueOf(Object obj) {
                return (obj == null) ? "null" : obj.toString();
            }
         */
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic";
        String land = "oneman";
        sea = land;
        land = land + "'s dreams";
        log(sea); // your answer? => oneman
        // TODO [comment] とても丁寧！いいですね。 by subaru (2020/04/22)
        // ありがとうございます
        // Pass by value と Pass by referenceを聞く
        // Java is strictly pass by value
        // ただし、Objectをpassする際にそのReference(Address in heap)をpass by valueする
        // Primitiveは本当のpass by value
        // immutableなObjectは新たな参照先を作る
        // mutableなObjectは既存の参照先を使い回す
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land; // 415
        sea = land.add(new BigDecimal(1)); //416
        sea.add(new BigDecimal(1)); //416
        log(sea); // your answer? => 417
        // wrong
        // immutableなので戻り値だけが変更される
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway; //null
    private int instanceDockside; // 0
    private Integer instanceHangar; // null
    private String instanceMagiclamp; //null

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => null
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => 0
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => null
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1|null|magician
        // Right
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor
        // Right
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor416
        // Right
        // TODO 回答としてはあってるけど、String もクラスであるため reference type だね。 by subaru (2020/04/22)
        // 確かにその通りです。すいません。ただ、immutableなクラスであるので
        // 値がPrimitive typeのように値自体がコピーされる意味（実際は、Heapに新たな参照できる値を作る）として捉えていました。
        // TODO [comment] Good!! by subaru (2020/04/23)

        // String = Primitive type
        // StringBuilder = class
        // "a" + "b" => StringBuilderに変換される
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => "harbor"
        // Right
        // in helpMethodArgumentVariable , sea reference new StringBuilder.
        // so there is no change of value sea before call
        // NOTE ここ補足しますが、helpMethodArgumentVariableでのseaはローカルスコープになったので結果は呼び元で反映しないのです winkichanwi
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    // TODO この書き方だと sea と land はインスタンス変数です。 by subaru (2020/04/22)
    // 修正しました

    // TODO kim いいですね、もう少しです by subaru (2020/04/23)
    // 少し発展だけど piari のアクセスレベルまで設定できるとより良いです。
    // public にすべきか、private にすべきかなど。
    // test_variable_writing メソッドのローカル変数として書き直してみよう！
    int piari;

    public void test_variable_writing() {
        String sea = "mystic";
        Integer land = null;

        log(sea + " , " + land + " , " + piari);
        // Right
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     *
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_variable_yourExercise() {
        // write your code here
        int i = 1;
        int j = 10;
        // TODO kim unused warning here by jflute (2020/04/23)
        int k = 100;
        String str = "www";
        log(str + i + j + " , " + str + (i + j));
    }
}
