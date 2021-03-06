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

import org.docksidestage.bizfw.basic.buyticket.OneDayTicket;
import org.docksidestage.bizfw.basic.buyticket.Ticket;
import org.docksidestage.bizfw.basic.buyticket.TicketBooth;
import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.BarkedSound;
import org.docksidestage.bizfw.basic.objanimal.BarkingProcess;
import org.docksidestage.bizfw.basic.objanimal.Cat;
import org.docksidestage.bizfw.basic.objanimal.Dog;
import org.docksidestage.bizfw.basic.objanimal.Tetrapod;
import org.docksidestage.bizfw.basic.objanimal.Tiger;
import org.docksidestage.bizfw.basic.objanimal.Zombie;
import org.docksidestage.bizfw.basic.objanimal.loud.AlarmClock;
import org.docksidestage.bizfw.basic.objanimal.loud.Loudable;
import org.docksidestage.bizfw.basic.objanimal.runner.FastRunner;
import org.docksidestage.javatry.basic.st6.dbms.Database;
import org.docksidestage.javatry.basic.st6.dbms.St6MySql;
import org.docksidestage.javatry.basic.st6.dbms.St6PostgreSql;
import org.docksidestage.javatry.basic.st6.os.St6MacOS;
import org.docksidestage.javatry.basic.st6.os.St6OldWindowsOS;
import org.docksidestage.javatry.basic.st6.os.St6OperationSystem;
import org.docksidestage.javatry.basic.st6.os.St6WindowsOS;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of object-oriented. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author kim sae young
 */
public class Step06ObjectOrientedTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        About Object
    //                                                                        ============
    // -----------------------------------------------------
    //                                        Against Object
    //                                        --------------
    /**
     * Fix several mistakes in buying one-day passport and in-park process. <br>
     * (OneDayPassportを買って InPark する処理の中で、間違いがいくつかあるので修正しましょう)
     */
    public void test_objectOriented_aboutObject_againstObject() {
        //
        // [ticket booth info]
        //
        int oneDayPrice = 7400;
        int quantity = 10;
        Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        int handedMoney = 10000;
        if (quantity <= 0) {
            throw new IllegalStateException("Sold out");
        }
        if (handedMoney < oneDayPrice) {
            throw new IllegalStateException("Short money: handedMoney=" + handedMoney);
        }
        --quantity;
        //salesProceeds = handedMoney;
        //salesProceedsは収益のことなので、TicketのPrice分だけを上がるべきなので
        salesProceeds = salesProceeds == null ? handedMoney : salesProceeds + handedMoney;

        //
        // [ticket info]
        //
        // int displayPrice = quantity;
        // Ticketの値段はquantityではなくoneDayPriceである
        int displayPrice = oneDayPrice;
        boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
            // throw new IllegalStateException("Already in park by this ticket: displayPrice=" + quantity);
        }
        alreadyIn = true;

        //
        // [final process]
        //

        //saveBuyingHistory(quantity, displayPrice, salesProceeds, alreadyIn);
        //宣伝されている変数の位置が違う
        saveBuyingHistory(quantity, salesProceeds, displayPrice, alreadyIn);
    }

    private void saveBuyingHistory(int quantity, Integer salesProceeds, int displayPrice, boolean alreadyIn) {
        if (alreadyIn) {
            // only logging here (normally e.g. DB insert)
            // showTicketBooth(displayPrice, salesProceeds);
            // showYourTicket(quantity, alreadyIn);
            showTicketBooth(quantity, salesProceeds);
            showYourTicket(displayPrice, alreadyIn);
        }
    }

    private void showTicketBooth(int quantity, Integer salesProceeds) {
        log("Ticket Booth: quantity={}, salesProceeds={}", quantity, salesProceeds);
    }

    private void showYourTicket(int displayPrice, boolean alreadyIn) {
        log("Ticket: displayPrice={}, alreadyIn={}", displayPrice, alreadyIn);
    }

    // -----------------------------------------------------
    //                                          Using Object
    //                                          ------------
    /**
     * Read (analyze) this code and compare with the previous test method, and think "what is object?". <br>
     * (このコードを読んで(分析して)、一つ前のテストメソッドと比べて、「オブジェクトとは何か？」を考えてみましょう)
     */
    /*
        1 コードが短くなっている
        2 変数名によっては、何をしているかを簡潔に読んでわかる
        3 ロジックが分けられているので、わかりやすい
        4 Objectごとに管理する情報がわけられているので管理がしやすい
     */
    public void test_objectOriented_aboutObject_usingObject() {
        //
        // [ticket booth info]
        //
        TicketBooth booth = new TicketBooth();

        // *booth has these properties:
        //int oneDayPrice = 7400;
        //int quantity = 10;
        //Integer salesProceeds = null;

        //
        // [buy one-day passport]
        //
        // _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
        // #fixme you if step05 has been finished, you can use this code by jflute (2019/06/15)
        // _/_/_/_/_/_/_/_/_/_/
        //Ticket ticket = booth.buyOneDayPassport(10000);
        booth.buyCertainDayPassport(1, 10000); // as temporary, remove if you finished steo05
        OneDayTicket ticket = new OneDayTicket(7400); // also here

        // *buyOneDayPassport() has this process:
        //if (quantity <= 0) {
        //    throw new TicketSoldOutException("Sold out");
        //}
        //if (handedMoney < oneDayPrice) {
        //    throw new TicketShortMoneyException("Short money: handedMoney=" + handedMoney);
        //}
        //--quantity;
        //salesProceeds = handedMoney;

        // *ticket has these properties:
        //int displayPrice = oneDayPrice;
        //boolean alreadyIn = false;

        // other processes here...
        // ...
        // ...

        //
        // [do in park now!!!]
        //
        ticket.doInPark();

        // *doInPark() has this process:
        //if (alreadyIn) {
        //    throw new IllegalStateException("Already in park by this ticket: displayPrice=" + displayPrice);
        //}
        //alreadyIn = true;

        //
        // [final process]
        //
        saveBuyingHistory(booth, ticket);
    }

    private void saveBuyingHistory(TicketBooth booth, Ticket ticket) {
        if (ticket.isAlreadyIn()) {
            // only logging here (normally e.g. DB insert)
            doShowTicketBooth(booth);
            doShowYourTicket(ticket);
        }
    }

    private void doShowTicketBooth(TicketBooth booth) {
        log("Ticket Booth: quantity={}, salesProceeds={}", booth.getQuantity(), booth.getSalesProceeds());
    }

    private void doShowYourTicket(Ticket ticket) {
        log("Your Ticket: displayPrice={}, alreadyIn={}", ticket.getDisplayPrice(), ticket.isAlreadyIn());
    }

    // ===================================================================================
    //                                                              Polymorphism Beginning
    //                                                              ======================
    /**
     * What string is sea and land variable at the method end? <br>
     * (メソッド終了時の変数 sea, land の中身は？)
     */
    public void test_objectOriented_polymorphism_1st_concreteOnly() {
        Dog dog = new Dog();
        BarkedSound sound = dog.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = dog.getHitPoint();
        log(land); // your answer? => 7
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_2nd_asAbstract() {
        Animal animal = new Dog();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // Right
        // これはPolymorphismに対する問題であると考えられる。
        // 親クラスのTypeで宣伝した変数が子クラスのインスタンスを持っている。
        // この場合は、子クラスが独自にもつメソッドには参照できない
        // ただし、子クラスの変数はインスタンスのあるところをさしているので型は親であっても子クラスと親クラス両方にあるメソッドは呼べる

        // TODO [question] Polymorphismの確かな動作原理 by kim
        // C++であるとvtableのようなポインタテーブルを用いてするといった流れがあったのですが。。。
        // JAVAでのPolymorphismが動作する理由、原理について知りたいです。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_3rd_fromMethod() {
        Animal animal = createAnyAnimal();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
        // Right
    }

    private Animal createAnyAnimal() {
        return new Dog();
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_4th_toMethod() {
        Dog dog = new Dog();
        doAnimalSeaLand_for_4th(dog);
        // Right
    }

    private void doAnimalSeaLand_for_4th(Animal animal) {
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => wan
        int land = animal.getHitPoint();
        log(land); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_5rd_overrideWithSuper() {
        Animal animal = new Cat();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => nya-
        int land = animal.getHitPoint();
        log(land); // your answer? => 5
        // Right
        // TODO [question] Javaではインスタンスにあるところに、メッセジーを送るみたいな話を聞いていますが by kim
        // その意味が何なのかが具体的にどうなのかが勉強しないといけない
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_6th_overriddenWithoutSuper() {
        Animal animal = new Zombie();
        BarkedSound sound = animal.bark();
        String sea = sound.getBarkWord();
        log(sea); // your answer? => uooo
        int land = animal.getHitPoint();
        log(land); // your answer? => -1
        // Right
        // まず、継承している場合継承している場合、一番親の方からコンストラクターを呼ぶべき
        // なので、指定がなければ親の方のデフォルトコンストラクターが呼ばれ続けて最後に自分のコンストラクタが呼ばれる
        // ただし、疑問が二つある
        // 1 コンストラクタの順番と関数の呼び出しについて
        // Animal animal = new Zombie(); からは
        // Animal()が呼ばれて次にZombie()が呼ばれると思った。
        // このときに、public Animal() {
        //        hitPoint = getInitialHitPoint();
        //    }
        // であり、getInitialHitPoint()はZombieの方でも宣伝されている。
        // ここで、疑問だったのは
        // "親のコンストラクタを呼び出している途中なのに、そこに使われた関数がオーバーライドされているものであれば、
        // オーバーライドされているものを呼べるのはなぜか"（親をコンストラクタしているならば関数も親のものだけ使うことが正しいかもと思った）
        // 2 呼び出している関数はオーバーライドされていないが、その中で使われる関数がオーバーライドされている時の呼び出しについて
        //     protected void prepareAbdominalMuscle() {
        //        logger.debug("...Using my abdominal muscle"); // dummy implementation
        //        downHitPoint();
        //    }
        // を呼ぶときに、Zombieクラスはこれがオーバーライドされていないのでこのままスーパークラスのものを呼ぶがその中にある
        // downHitPoint()はオーバーライド版がある。この場合に、なぜdownHitPoint()だけはオーバーライド版が呼べるか
        // Polymorphismの具体的な動作原理を知ることが大事だと思った。
    }

    // ===================================================================================
    //                                                              Polymorphism Interface
    //                                                              ======================
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_dispatch() {
        Loudable loudable = new Zombie();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => uooo
        String land = ((Zombie) loudable).bark().getBarkWord();
        log(land); // your answer? => uooo
        // Right
        // 一般的には親クラスに子インスタンスを割り当てる
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_hierarchy() {
        Loudable loudable = new AlarmClock();
        String sea = loudable.soundLoudly();
        log(sea); // your answer? => jiri jiri jiri---
        boolean land = loudable instanceof Animal;
        log(land); // your answer? => false
        // Right
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_objectOriented_polymorphism_interface_partImpl() {
        Animal seaAnimal = new Cat();
        Animal landAnimal = new Zombie();
        boolean sea = seaAnimal instanceof FastRunner;
        log(sea); // your answer? => true
        boolean land = landAnimal instanceof FastRunner;
        log(land); // your answer? => false
        // Right
    }

    /**
     * Make Dog class implement FastRunner interface. (the implementation is same as Cat class) <br>
     * (DogもFastRunnerインターフェースをimplementsしてみましょう (実装はCatと同じで))
     */
    public void test_objectOriented_polymorphism_interface_runnerImpl() {
        FastRunner dog = new Dog();
        FastRunner cat = new Cat();

        dog.run();
        cat.run();
    }

    // ===================================================================================
    //                                                                 Polymorphism Making
    //                                                                 ===================
    /**
     * Make concrete class of Animal, which is not FastRunner, in "objanimal" package. (implementation is as you like) <br>
     * (FastRunnerではないAnimalクラスのコンクリートクラスをobjanimalパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeConcrete() {
        Animal a = new Tiger();
        String barkWord = a.bark().getBarkWord();
        log(barkWord);
    }

    /**
     * Make interface implemented by part of Animal concrete class in new package under "objanimal" package. (implementation is as you like) <br>
     * (Animalクラスの一部のコンクリートクラスだけがimplementsするインターフェースをobjanimal配下の新しいパッケージに作成しましょう (実装はお好きなように))
     */
    public void test_objectOriented_polymorphism_makeInterface() {
        /*
        Animal c = new Cat();
        Animal d = new Dog();
        Animal t = new Tiger();
        
        cannot use of Tetrapod class.
         */

        Tetrapod c = new Cat();
        Tetrapod d = new Dog();
        Tetrapod t = new Tiger();

        c.howToLook();
        c.walkByFourLegs();
        d.howToLook();
        d.walkByFourLegs();
        t.howToLook();
        t.walkByFourLegs();

        // we cannot use Animal class method
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Extract St6MySql, St6PostgreSql (basic.st6.dbms)'s process to abstract class (as super class and sub-class) <br>
     * (St6MySql, St6PostgreSql (basic.st6.dbms) から抽象クラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */

    public void test_objectOriented_writing_generalization_extractToAbstract() {
        // done kim "int offset = pageSize * (pageNumber - 1)" が全く同じなので、再利用したいですね by jflute (2020/05/20)
        // "テンプレートメソッドパターン" (Template Method Pattern) を調べてみてください。
        // Done kim [tips] 一応、テンプレートメソッドパターンについて... by jflute (2020/05/20)
        // Databaseクラスに、buildPagingQuery() を持たせて...
        //
        //  public String buildPagingQuery(int pageSize, int pageNumber) {
        //      int offset = calcOffSet(pageSize, pageNumber);
        //      return buildPagingStatement(offset, ...);
        //  }
        //
        //  protected abstract String buildPagingStatement(...) {
        //  }
        //
        // というように、フロー (流れ) 全体を再利用して、細かい詳細だけサブクラスで実装すると再利用率が高いです。
        // フロー(流れ)も再利用しているので。

        Database ms = new St6MySql();
        Database ps = new St6PostgreSql();

        log(ms.buildPagingQuery(5, 2));
        log(ps.buildPagingQuery(5, 2));
        // your confirmation code here
    }

    /**
     * Extract St6OperationSystem (basic.st6.os)'s process to concrete classes (as super class and sub-class) <br>
     * (St6OperationSystem (basic.st6.os) からコンクリートクラスを抽出してみましょう (スーパークラスとサブクラスの関係に))
     */
    public void test_objectOriented_writing_specialization_extractToConcrete() {
        // done kim "super.setOsType("Mac");" は、super. は不要です by jflute (2020/05/20)
        // 逆に、super. を付けてしまうと、そのメソッドをオーバーライドしたときに、this のメソッドが呼ばれなくなってしまいます。
        // 確かにその通りです！
        // done kim せっかく MacOS, WindowsOS と分かれているので、St6OperationSystem 内の if の分岐を無くそう by jflute (2020/05/20)
        // コンクリートクラスを抽出したからには、St6OperationSystem の中には MacOS や WindowsOS という言葉を登場させないように。
        String loginIdForMac = "s.kim.for.mac@bizreach.co.jp";
        String relativePath = "javatry/basic/st6";
        St6OperationSystem mac = new St6MacOS(loginIdForMac);
        log(mac.buildUserResourcePath(relativePath));

        String loginIdForWindows = "s.kim.for.windows@bizreach.co.jp";
        St6OperationSystem windows = new St6WindowsOS(loginIdForWindows);
        log(windows.buildUserResourcePath(relativePath));

        String loginIdForOldWindows = "s.kim.for.oldwindows@bizreach.co.jp";
        St6OperationSystem oldWindows = new St6OldWindowsOS(loginIdForOldWindows);
        log(oldWindows.buildUserResourcePath(relativePath));
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Extract Animal's bark() process as BarkingProcess class to also avoid large abstract class. <br>
     * (抽象クラス肥大化を抑制するためにも、Animalのbark()のプロセス(処理)をBarkingProcessクラスとして切り出しましょう)
     */
    public void test_objectOriented_writing_withDelegation() {
        // Done kim "Animal extends BarkingProcess" になっているけど、"Animal is a BarkingProcess" だろうか？ by jflute (2020/05/20)
        // 意味的に is-a の関係になっていないものを継承しないようにしよう。
        // done kim ありがとう。いいね。BarkingProcessの... by jflute (2020/05/20)
        //
        // private Animal animal = null;
        //
        // ですが、
        //
        // BarkingProcess は Immutable でいいので、
        //
        // private final Animal animal;
        //
        // というように、finalを付けて、コンストラクタでの初期化を前提にしてよいかと思います。
        //
        BarkingProcess bpc = new BarkingProcess(new Cat());
        BarkingProcess bpd = new BarkingProcess(new Dog());
        BarkingProcess bpz = new BarkingProcess(new Zombie());

        log(bpc.bark().getBarkWord());
        log(bpd.bark().getBarkWord());
        log(bpz.bark().getBarkWord());
        // your confirmation code here
    }

    /**
     * Is Zombie correct as sub-class of Animal? Analyze it in thirty seconds. (thinking only) <br>
     * (ゾンビは動物クラスのサブクラスとして適切でしょうか？30秒だけ考えてみましょう (考えるだけでOK))
     */
    public void test_objectOriented_zoo() {
        // do nothing here
    }
}
