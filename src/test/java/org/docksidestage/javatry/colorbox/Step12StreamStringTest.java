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
package org.docksidestage.javatry.colorbox;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.docksidestage.bizfw.colorbox.ColorBox;
import org.docksidestage.bizfw.colorbox.yours.YourPrivateRoom;
import org.docksidestage.unit.PlainTestCase;

/**
 * The test of String with color-box, using Stream API you can. <br>
 * Show answer by log() for question of javadoc.
 * @author jflute
 * @author s.kim
 */
public class Step12StreamStringTest extends PlainTestCase {

    // ===================================================================================
    //                                                                            length()
    //                                                                            ========
    /**
     * What is color name length of first color-box? <br>
     * (最初のカラーボックスの色の名前の文字数は？)
     */
    public void test_length_basic() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String answer = colorBoxList.stream()
                .findFirst()
                .map(colorBox -> colorBox.getColor().getColorName())
                .map(colorName -> colorName.length() + " (" + colorName + ")")
                .orElse("*not found");
        log(answer);
    }

    /**
     * Which string has max length in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長い文字列は？)
     */
    public void test_length_findMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        //
        // done kim boxSpace.getContent().getClass().equals(String.class)は... by jflute (2020/05/20)
        // boxSpace.getContent() instanceof String でOKです。(この場合、どっちでも大丈夫だけど、instanceofの方がシンプルかな)
        //
        // done kim .map(boxSpace -> boxSpace.getContent().toString())は... by jflute (2020/05/20)
        // .map(boxSpace -> (String)boxSpace.getContent())
        // というようにダウンキャストでOK。その一つまで中身のインスタンスが String であると断定しているから。
        // toString()使ってると、IntegerとかDateとかも混ざるのかな？って思ってしまう。
        //
        // done kim この辺は、ある程度は自由なんだけど、getContent() が多すぎるので... by jflute (2020/05/20)
        // .map(boxSpace -> boxSpace.getContent())
        // .filter(content -> content instanceof String)
        // .map(content -> (String)content)
        // ...
        // というように書くともうちょいスッキリします。
        // いつ map() するか？ いつ filter() するか、うまく調整しよう。
        //
        // done kim ありがとう。content != null の filter は、要らないと思いますがどうでしょう？ by jflute (2020/05/20)
        // その一つ前の .map(boxSpace -> boxSpace.getContent()) で null が戻ってきたら自然と filtering されているはずです。
        // そうじゃないにしても、content instanceof String が null は除外されるはずです。
        String answer = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .map(content -> (String) content)
                .max(Comparator.comparingInt(str -> str.length()))
                .orElse("*not found");
        log(answer);
    }

    /**
     * How many characters are difference between max and min length of string in color-boxes? <br>
     * (カラーボックスに入ってる文字列の中で、一番長いものと短いものの差は何文字？)
     */
    public void test_length_findMaxMinDiff() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int maxLength = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .mapToInt(content -> ((String) content).length())
                .max().orElseThrow(() -> new RuntimeException("there is empty list of color box"));

        int minLength = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .mapToInt(content -> ((String) content).length())
                .min().orElseThrow(() -> new RuntimeException("there is empty list of color box"));

        log("difference : " + (maxLength - minLength));
    }

    // has small #adjustmemts from ClassicStringTest
    //  o sort allowed in Stream
    /**
     * Which value (toString() if non-string) has second-max length in color-boxes? (sort allowed in Stream)<br>
     * (カラーボックスに入ってる値 (文字列以外はtoString()) の中で、二番目に長い文字列は？ (Streamでのソートありで))
     */
    public void test_length_findSecondMax() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxSecondString = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .map(content -> String.valueOf(content))
                .sorted(Comparator.comparingInt(String::length).reversed())
                .skip(1)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("the length of list <= 1"));
        log(maxSecondString);
    }

    /**
     * How many total lengths of strings in color-boxes? <br>
     * (カラーボックスに入ってる文字列の長さの合計は？)
     */
    public void test_length_calculateLengthSum() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int sumOfLength = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String)
                .mapToInt(content -> ((String)content).length())
                .sum();
        log(sumOfLength);
    }

    /**
     * Which color name has max length in color-boxes? <br>
     * (カラーボックスの中で、色の名前が一番長いものは？)
     */
    public void test_length_findMaxColorSize() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String maxLengthColorName = colorBoxList.stream()
                .map(colorBox -> colorBox.getColor())
                .map(color -> color.getColorName())
                .max(Comparator.comparingInt(String::length))
                .orElse(null);

        log(maxLengthColorName);
    }

    // ===================================================================================
    //                                                            startsWith(), endsWith()
    //                                                            ========================
    /**
     * What is color in the color-box that has string starting with "Water"? <br>
     * ("Water" で始まる文字列をしまっているカラーボックスの色は？)
     */
    public void test_startsWith_findFirstWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> colorNamesStartsWithWater = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream()
                            .map(boxSpace -> boxSpace.getContent())
                            .anyMatch(content -> content instanceof String && ((String)content).startsWith("Water"))
                )
                .map(colorBox -> colorBox.getColor().getColorName())
                .collect(Collectors.toList());

        log(colorNamesStartsWithWater);
    }

    /**
     * What is color in the color-box that has string ending with "front"? <br>
     * ("front" で終わる文字列をしまっているカラーボックスの色は？)
     */
    public void test_endsWith_findLastWord() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        List<String> colorNamesEndsWithfront = colorBoxList.stream()
                .filter(colorBox -> colorBox.getSpaceList().stream()
                        .map(boxSpace -> boxSpace.getContent())
                        .anyMatch(content -> content instanceof String && ((String)content).endsWith("front"))
                )
                .map(colorBox -> colorBox.getColor().getColorName())
                .collect(Collectors.toList());

        log(colorNamesEndsWithfront);
    }

    // ===================================================================================
    //                                                            indexOf(), lastIndexOf()
    //                                                            ========================
    /**
     * What number character is starting with first "front" of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列で、最初の "front" は何文字目から始まる？)
     */
    public void test_indexOf_findIndex() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int position = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String && ((String)content).endsWith("front"))
                .map(content -> (String)content)
                .findFirst()
                .orElseGet(() -> {
                    throw new RuntimeException("there is no word that ends with 'front'");
                })
                .indexOf("front");

        log(position);
    }

    /**
     * What number character is starting with the late "ど" of string containing plural "ど"s in color-boxes? (e.g. "どんどん" => 3) <br>
     * (カラーボックスに入ってる「ど」を二つ以上含む文字列で、最後の「ど」は何文字目から始まる？ (e.g. "どんどん" => 3))
     */
    public void test_lastIndexOf_findIndex() {

        class Match {
            Match() {}
            boolean containsAtLeastTwoTimes(String target, String part) {
                if(target == null || part == null) return false;

                int indexOfPart = target.indexOf(part);
                int lastIndexOfPart = target.lastIndexOf(part);

                return indexOfPart != -1 && indexOfPart != lastIndexOfPart;
            }
        };

        // for boolean method
        Match match = new Match();

        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        int position = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String && match.containsAtLeastTwoTimes((String)content,"ど"))
                .map(content -> (String)content)
                .findFirst()
                .orElseGet(() -> {
                    throw new RuntimeException("there is no word that contains 'ど' at least two times");
                })
                .lastIndexOf("ど");

        log(position);
    }

    // ===================================================================================
    //                                                                         substring()
    //                                                                         ===========
    /**
     * What character is first of string ending with "front" in color-boxes? <br>
     * (カラーボックスに入ってる "front" で終わる文字列の最初の一文字は？)
     */
    public void test_substring_findFirstChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        char startChar = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String && ((String)content).endsWith("front"))
                .map(content -> (String)content)
                .findFirst()
                .orElseGet(() -> {
                    throw new RuntimeException("there is no word that ends with 'front'");
                })
                .charAt(0);

        log(startChar);
    }

    /**
     * What character is last of string starting with "Water" in color-boxes? <br>
     * (カラーボックスに入ってる "Water" で始まる文字列の最後の一文字は？)
     */
    public void test_substring_findLastChar() {
        List<ColorBox> colorBoxList = new YourPrivateRoom().getColorBoxList();
        String target = colorBoxList.stream()
                .flatMap(colorBox -> colorBox.getSpaceList().stream())
                .map(boxSpace -> boxSpace.getContent())
                .filter(content -> content instanceof String && ((String)content).startsWith("Water"))
                .map(content -> (String)content)
                .findFirst()
                .orElseGet(() -> {
                    throw new RuntimeException("there is no word that ends with 'front'");
                });

        log(target.charAt(target.length()-1));
    }

    // ===================================================================================
    //                                                                           replace()
    //                                                                           =========
    /**
     * How many characters does string that contains "o" in color-boxes and removing "o" have? <br>
     * (カラーボックスに入ってる "o" (おー) を含んだ文字列から "o" を全て除去したら何文字？)
     */
    public void test_replace_remove_o() {
    }

    /**
     * What string is path string of java.io.File in color-boxes, which is replaced with "/" to Windows file separator? <br>
     * カラーボックスに入ってる java.io.File のパス文字列のファイルセパレーターの "/" を、Windowsのファイルセパレーターに置き換えた文字列は？
     */
    public void test_replace_fileseparator() {
    }

    // ===================================================================================
    //                                                                    Welcome to Devil
    //                                                                    ================
    /**
     * What is total length of text of DevilBox class in color-boxes? <br>
     * (カラーボックスの中に入っているDevilBoxクラスのtextの長さの合計は？)
     */
    public void test_welcomeToDevil() {
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * What string is converted to style "map:{ key = value ; key = value ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = value ; ... }" という形式で表示すると？)
     */
    public void test_showMap_flat() {
    }

    /**
     * What string is converted to style "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" from java.util.Map in color-boxes? <br>
     * (カラーボックスの中に入っている java.util.Map を "map:{ key = value ; key = map:{ key = value ; ... } ; ... }" という形式で表示すると？)
     */
    public void test_showMap_nested() {
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    // has small #adjustmemts from ClassicStringTest
    //  o comment out because of too difficult to be stream?
    ///**
    // * What string of toString() is converted from text of SecretBox class in upper space on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのupperスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_flat() {
    //}
    //
    ///**
    // * What string of toString() is converted from text of SecretBox class in both middle and lower spaces on the "white" color-box to java.util.Map? <br>
    // * (whiteのカラーボックスのmiddleおよびlowerスペースに入っているSecretBoxクラスのtextをMapに変換してtoString()すると？)
    // */
    //public void test_parseMap_nested() {
    //}
}
