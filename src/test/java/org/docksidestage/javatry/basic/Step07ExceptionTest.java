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

import java.io.File;
import java.io.IOException;

import org.docksidestage.bizfw.basic.supercar.SupercarClient;
import org.docksidestage.javatry.basic.st7.St7ConstructorChallengeException;
import org.docksidestage.unit.PlainTestCase;

// Done kim authorの修正をお願いしますー by jflute (2020/05/20)
/**
 * The test of variable. <br>
 * Operate as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りに実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author s.kim
 */
public class Step07ExceptionTest extends PlainTestCase {

    // ===================================================================================
    //                                                                             Message
    //                                                                             =======
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_message_basic() {
        IllegalStateException exp = new IllegalStateException("mystic");
        String sea = exp.getMessage();
        log(sea); // your answer? => mystic

        // getMessageはThrowableクラスに宣伝されているもの
        // IllegalStateException extends RuntimeException extends Exception extends Throwable
        // getMessage()の戻り値はThrowableインスタンスの詳細エラーメッセージ
    }

    // ===================================================================================
    //                                                                           Hierarchy
    //                                                                           =========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_exception_hierarchy_Runtime_instanceof_RuntimeException() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof RuntimeException;
        log(sea); // your answer? => true
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Exception() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => true
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Error() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Error;
        log(sea); // your answer? => false
        // Right
        // Error extends Throwable
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Runtime_instanceof_Throwable() {
        Object exp = new IllegalStateException("mystic");
        boolean sea = exp instanceof Throwable;
        log(sea); // your answer? => true
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_exception_hierarchy_Throwable_instanceof_Exception() {
        Object exp = new Throwable("mystic");
        boolean sea = exp instanceof Exception;
        log(sea); // your answer? => false
        // Right
    }

    // ===================================================================================
    //                                                                   Checked Exception
    //                                                                   =================
    /**
     * Show canonical path of new java.io.File(".") by log(), and if I/O error, show message and stack-trace instead <br>
     * (new java.io.File(".") の canonical path を取得してログに表示、I/Oエラーはメッセージとスタックトレースを代わりに)
     */
    public void test_exception_checkedException_basic() {
        File f = new File(".");

        try {
            log(f.getCanonicalPath());
        } catch (IOException e) {
            // done kim スタックトレース (stack-trace) が表示されていないです by jflute (2020/05/20)
            log("cannot get canonical path : " + e.getMessage(), e);
        }
        // done kim 不要なセミコロン？ by jflute (2020/05/20)
        // IOException extends Exception extends Throwable extends Object
        // get project path
    }

    // ===================================================================================
    //                                                                               Cause
    //                                                                               =====
    /**
     * What string is sea variable in the catch block?
     * And What is exception class name displayed at the last "Caused By:" of stack trace? <br>
     * (catchブロックの変数 sea, land の中身は？また、スタックトレースの最後の "Caused By:" で表示されている例外クラス名は？)
     */
    public void test_exception_cause_basic() {
        String sea = "mystic";
        String land = "oneman";
        try {
            throwCauseFirstLevel();
            fail("always exception but none");
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();
            sea = cause.getMessage();
            land = cause.getClass().getSimpleName();
            log(sea); // your answer? => Failed to call the third help method:  -1
            // right
            log(land); // your answer? => Throwable
            // wrong
            // IllegalArgumentException
            log(e); // your answer? => Failed to call the second help method:  -1
            // wrong
            // java.lang.IllegalStateException: Failed to call the second help method: -1 and
            // stack trace
        }
    }

    private void throwCauseFirstLevel() {
        int count = -1;
        try {
            throwCauseSecondLevel(count);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException("Failed to call the second help method: " + count, e);
        }
    }

    private void throwCauseSecondLevel(int count) {
        try {
            if (count < 0) {
                throwCauseThirdLevel(count);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to call the third help method: " + count, e);
        }
    }

    private void throwCauseThirdLevel(int count) {
        if (count < 0) {
            Integer.valueOf("piari");
        }
    }

    // ===================================================================================
    //                                                                         Translation
    //                                                                         ===========
    /**
     * Execute this test and read exception message and write situation and cause on comment. <br>
     * テストを実行して、例外メッセージを読んで、状況と原因をコメント上に書きましょう。
     */
    public void test_exception_translation_debugChallenge() {
        try {
            new SupercarClient().buySupercar();
            fail("always exception but none");
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
            // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
            // What happens? Write situation and cause here. (何が起きた？状況と原因をここに書いてみましょう)
            // - - - - - - - - - -
            // org.docksidestage.bizfw.basic.screw.SpecialScrewManufacturer$SpecialScrewCannotMakeBySpecException: The kawaii face is not useful to make screw: ScrewSpec:{\(^_^)/}
            // 呼ばれた順番に下から現れると思う
            // エラーの原因は笑顔のものが入っていたこと。
            // 下に行くほど最近呼ばれたものになる（本当の原因には遠くなる）
            // _/_/_/_/_/_/_/_/_/_/
        }
    }

    /**
     * Improve exception handling in supercar's classes to understand the situation
     * by only exception information as far as possible. <br>
     * できるだけ例外情報だけでその状況が理解できるように、Supercarのクラスたちの例外ハンドリングを改善しましょう。
     */
    public void test_exception_translation_improveChallenge() {
        try {
            // done kim [いいね]例外の翻訳 (Exception Translation) がうまくできてます！！！素晴らしい by jflute (2020/05/20)
            // これで、例外メッセージとスタックトレースだけで、だいぶ状況が把握できるようになりますね。
            // done kim 一方で...例外メッセージですが、文章の中に値を埋め込む時はクォート(quote)すると良いです by jflute (2020/05/20)
            //
            // "Catalog key of " + catalogKey + " is not available to make super car"
            //   ↓↓↓
            // "Catalog key of '" + catalogKey + "' is not available to make super car"
            //
            // どれが動的な値で、どれが固定的な文章なのか、すぐに判断が付くように
            //
            new SupercarClient().buySupercar(); // you can fix the classes
            fail("always exception but none");

            // done kim チェック例外 (checked exception) は throw されないメソッドなので...catchするのはExceptionじゃなくてRuntimeExceptionで良いです by jflute (2020/05/20)
            // Exception を catch するときは、チェック例外がthrowされるメソッドを呼び出したときです。
            // http://dbflute.seasar.org/ja/manual/topic/programming/java/exception.html
        } catch (RuntimeException e) {
            log("*No hint here for training.", e);
        }
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Fix terrible (YABAI in Japanese) exception handling. (you can modify exception class) <br>
     * (やばい例外ハンドリングがあるので修正しましょう (例外クラスを修正してOK))
     */
    // 何が問題かがわからないLogになっていたので問題になっていたと思う。
    public void test_exception_writing_constructorChallenge() {
        try {
            helpSurprisedYabaiCatch();
        } catch (St7ConstructorChallengeException e) {
            log("Thrown by help method", e); // should show also "Caused-by" information
        }
    }

    private void helpSurprisedYabaiCatch() {
        try {
            helpThrowIllegalState();
        } catch (IllegalStateException e) {
            throw new St7ConstructorChallengeException("Failed to do something.", e);
        }
    }

    private void helpThrowIllegalState() { // simple implementation here
        throw new IllegalStateException("something illegal");
    }
}
