package com.ryanwelch.wordstore;

import java.util.*;

public class WordGen {

    private Random rand;

    public WordGen(long seed) {
        rand = new Random(seed);
    }

    public String make() {
        double a = 0.06;
        double b = a + 0.06;
        double c = b + 0.075;
        double d = c + 0.05;
        double e = d + 0.045;
        double f = e + 0.04;
        double g = f + 0.03;
        double h = g + 0.035;
        double i = h + 0.04;
        double j = i + 0.01;
        double k = j + 0.015;
        double l = k + 0.03;
        double m = l + 0.045;
        double n = m + 0.03;
        double o = n + 0.025;
        double p = o + 0.07;
        double q = p + 0.005;
        double r = q + 0.06;
        double s = r + 0.135;
        double t = s + 0.065;
        double u = t + 0.025;
        double v = u + 0.015;
        double w = v + 0.025;
        double y = w + 0.005;

        String word;
        double db = rand.nextDouble();
        if (db < a)
            word = afterA("a");
        else if (db < b)
            word = afterBPInit("b");
        else if (db < c)
            word = afterCInit("c");
        else if (db < d)
            word = afterDInit("d");
        else if (db < e)
            word = afterE("e");
        else if (db < f)
            word = afterFInit("f");
        else if (db < g)
            word = afterGInit("g");
        else if (db < h)
            word = necVowel("h");
        else if (db < i)
            word = afterI("i");
        else if (db < j)
            word = necVowel("j");
        else if (db < k)
            word = afterKInit("k");
        else if (db < l)
            word = necVowel("l");
        else if (db < m)
            word = necVowel("m");
        else if (db < n)
            word = necVowel("n");
        else if (db < o)
            word = afterO("o");
        else if (db < p)
            word = afterBPInit("p");
        else if (db < q)
            word = afterQInit("qu");
        else if (db < r)
            word = necVowel("r");
        else if (db < s)
            word = afterSInit("s");
        else if (db < t)
            word = afterTInit("t");
        else if (db < u)
            word = afterU("u");
        else if (db < v)
            word = necVowel("v");
        else if (db < w)
            word = afterWInit("w");
        else if (db < y)
            word = necVowel("y");
        else
            word = necVowel("z");
        return word;
    }

    private boolean isVowel(char ch) {
        return (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u');
    }

    private String necVowel(String word) {
        double y = 0.01;
        double a = y + 0.28;
        double e = a + 0.18;
        double i = e + 0.18;
        double o = i + 0.23;
        double db = rand.nextDouble();
        if (db < y)
            word = word + 'y';
        else if (db < a)
            word = afterA(word + 'a');
        else if (db < e)
            word = afterE(word + 'e');
        else if (db < i)
            word = afterI(word + 'i');
        else if (db < o)
            word = afterO(word + 'o');
        else
            word = afterU(word + 'u');
        return word;
    }

    private String afterA(String word) {
        double db = rand.nextDouble();
        if (db < 0.003)
            word = afterVowel(word + 'w');
        else if (db < 0.02)
            word = afterVowel(word + 'i');
        else if (db < 0.035)
            word = afterVowel(word + 'u');
        else if (db < 0.052)
            word = afterVowel(word + 'e');
        else if (db < 0.057)
            word = afterVowel(word + 'o');
        else
            word = afterVowel(word);
        return word;
    }

    private String afterBPInit(String word) {
        double db = rand.nextDouble();
        if (db < 0.05)
            word = necVowel(word + 'l');
        else if (db < 0.2)
            word = necVowel(word + 'r');
        else
            word = necVowel(word);
        return word;
    }

    private String afterCInit(String word) {
        double a = 0.22;
        double o = a + 0.4;
        double u = o + 0.07;
        double h = u + 0.14;
        double l = h + 0.07;
        double db = rand.nextDouble();
        if (db < a)
            word = afterA(word + 'a');
        else if (db < o)
            word = afterO(word + 'o');
        else if (db < u)
            word = afterU(word + 'u');
        else if (db < h)
            word = necVowel(word + 'h');
        else if (db < l)
            word = necVowel(word + 'l');
        else
            word = necVowel(word + 'r');
        return word;
    }

    private String afterDInit(String word) {
        double db = rand.nextDouble();
        if (db < 0.1)
            word = necVowel(word + 'r');
        else if (db < 0.11)
            word = necVowel(word + 'w');
        else
            word = necVowel(word);
        return word;
    }

    private String afterE(String word) {
        double db = rand.nextDouble();
        if (db < 0.001)
            word = afterVowel(word + 'w');
        else if (db < 0.02)
            word = afterVowel(word + 'a');
        else if (db < 0.04)
            word = afterVowel(word + 'e');
        else if (db < 0.045)
            word = afterVowel(word + 'o');
        else if (db < 0.05)
            word = afterVowel(word + 'u');
        else if (db < 0.06)
            word = afterVowel(word + 'i');
        else
            word = afterVowel(word);
        return word;
    }

    private String afterFInit(String word) {
        double db = rand.nextDouble();
        if (db < 0.1)
            word = necVowel(word + 'r');
        else if (db < 0.30)
            word = necVowel(word + 'l');
        else
            word = necVowel(word);
        return word;
    }

    private String afterGInit(String word) {
        double db = rand.nextDouble();
        if (db < 0.2)
            word = necVowel(word + 'r');
        else if (db < 0.30)
            word = necVowel(word + 'l');
        else
            word = necVowel(word);
        return word;
    }

    private String afterI(String word) {
        double db = rand.nextDouble();
        if (db < 0.025)
            word = afterVowel(word + 'a');
        else if (db < 0.04)
            word = afterVowel(word + 'e');
        else if (db < 0.07)
            word = afterVowel(word + 'o');
        else if (db < 0.1)
            word = afterVowel(word + 'u');
        else
            word = afterVowel(word);
        return word;
    }

    private String afterKInit(String word) {
        double db = rand.nextDouble();
        if (db < 0.1)
            word = necVowel(word + 'n');
        else if (db < 0.55)
            word = afterE(word + 'e');
        else
            word = afterI(word + 'i');
        return word;
    }

    private String afterO(String word) {
        double db = rand.nextDouble();
        if (db < 0.004)
            word = afterVowel(word + 'w');
        else if (db < 0.02)
            word = afterVowel(word + 'a');
        else if (db < 0.05)
            word = afterVowel(word + 'i');
        else if (db < 0.07)
            word = afterVowel(word + 'o');
        else if (db < 0.097)
            word = afterVowel(word + 'u');
        else if (db < 0.1)
            word = afterVowel(word + 'e');
        else
            word = afterVowel(word);
        return word;
    }

    private String afterQInit(String word) {
        double db = rand.nextDouble();
        if (db < 0.375)
            word = afterA(word + 'a');
        else if (db < 0.5)
            word = afterE(word + 'e');
        else if (db < 0.875)
            word = afterI(word + 'i');
        else
            word = afterO(word + 'o');
        return word;
    }

    private String afterSInit(String word) {
        double cl = 0.01;
        double cr = cl + 0.03;
        double c = cr + 0.06;
        double h = c + 0.09;
        double hr = h + 0.01;
        double k = hr + 0.02;
        double l = k + 0.03;
        double m = l + 0.01;
        double n = m + 0.02;
        double p = n + 0.03;
        double q = p + 0.01;
        double t = q + 0.12;
        double tr = t + 0.05;
        double w = tr + 0.03;
        double db = rand.nextDouble();
        if (db < cl)
            word = necVowel(word + "cl");
        else if (db < cr)
            word = necVowel(word + "cr");
        else if (db < c) {
            db = rand.nextDouble();
            if (db < 0.47)
                word = afterA(word + "ca");
            else if (db < 0.84)
                word = afterO(word + "co");
            else
                word = afterU(word + "cu");
        } else if (db < h)
            word = necVowel(word + "h");
        else if (db < hr)
            word = necVowel(word + "hr");
        else if (db < k) {
            db = rand.nextDouble();
            if (db < 0.005)
                word = word + "ky";
            else if (db < 0.5)
                word = afterE(word + "ke");
            else
                word = afterI(word + "ki");
        } else if (db < l)
            word = necVowel(word + "l");
        else if (db < m)
            word = necVowel(word + "m");
        else if (db < n)
            word = necVowel(word + "n");
        else if (db < p)
            word = afterBPInit(word + "p");
        else if (db < q)
            word = afterQInit(word + "qu");
        else if (db < t)
            word = necVowel(word + "t");
        else if (db < tr)
            word = necVowel(word + "tr");
        else if (db < w)
            word = necVowel(word + "w");
        else
            word = necVowel(word);
        return word;
    }

    private String afterTInit(String word) {
        double db = rand.nextDouble();
        if (db < 0.3)
            word = necVowel(word + 'r');
        else if (db < 0.42)
            word = necVowel(word + 'h');
        else if (db < 0.445)
            word = necVowel(word + "hr");
        else if (db < 0.47)
            word = necVowel(word + 'w');
        else
            word = necVowel(word);
        return word;
    }

    private String afterWInit(String word) {
        double db = rand.nextDouble();
        if (db < 0.1)
            word = necVowel(word + 'h');
        else
            word = necVowel(word);
        return word;
    }

    private String afterU(String word) {
        double db = rand.nextDouble();
        if (db < 0.02)
            word = afterVowel(word + 'a');
        else if (db < 0.03)
            word = afterVowel(word + 'e');
        else if (db < 0.045)
            word = afterVowel(word + 'o');
        else if (db < 0.065)
            word = afterVowel(word + 'i');
        else
            word = afterVowel(word);
        return word;
    }

    private int badcount(String word) {
        int bad = 0;
        for (int i = 1; i < word.length(); i++)
            if (isVowel(word.charAt(i)) && isVowel(word.charAt(i - 1)))
                bad++;
            else if (!isVowel(word.charAt(i)) && !isVowel(word.charAt(i - 1)))
                bad++;
            else if (word.charAt(i) == 'j' || word.charAt(i) == 'k' || word.charAt(i) == 'z')
                bad++;
        return bad;
    }

    private String afterVowel(String word) {
        double vo = 0.01;
        double sp = 0.055;
        double b = sp + 0.022;
        double c = b + 0.035;
        double d = c + 0.036;
        double f = d + 0.023;
        double g = f + 0.026;
        double h = g + 0.021;
        double j = h + 0.008;
        double k = j + 0.008;
        double l = k + 0.075;
        double m = l + 0.063;
        double n = m + 0.163;
        double p = n + 0.026;
        double q = p + 0.005;
        double r = q + 0.12;
        double s = r + 0.153;
        double t = s + 0.123;
        double v = t + 0.01;
        double w = v + 0.005;
        double x = w + 0.01;
        double y = x + 0.005;
        double db = rand.nextDouble();
        if (db < sp)
            if (word.length() == 1 ||
                    (word.length() == 2 && isVowel(word.charAt(0)) && isVowel(word.charAt(1))))
                if (db < vo)
                    return word;
                else
                    return afterVowel(word);
            else
                return word;
        else if (word.length() > 9 && rand.nextDouble() < 0.5)
            return word;
        else if (badcount(word) > 5 && rand.nextDouble() < 0.5)
            return word;
        else if (db < b)
            word = afterB(word + "b");
        else if (db < c)
            word = afterC(word + "c");
        else if (db < d)
            word = afterD(word + "d");
        else if (db < f)
            word = afterF(word + "f");
        else if (db < g)
            word = afterG(word + "g");
        else if (db < h)
            word = secVowel(word + "h");
        else if (db < j)
            word = secVowel(word + "j");
        else if (db < k)
            word = afterK(word + "k");
        else if (db < l)
            word = afterL(word + "l");
        else if (db < m)
            word = afterM(word + "m");
        else if (db < n)
            word = afterN(word + "n");
        else if (db < p)
            word = afterP(word + "p");
        else if (db < q)
            word = afterQ(word + "qu");
        else if (db < r)
            word = afterR(word + "r");
        else if (db < s)
            word = afterS(word + "s");
        else if (db < t)
            word = afterT(word + "t");
        else if (db < v)
            word = afterV(word + "v");
        else if (db < w)
            word = secVowel(word + "w");
        else if (db < x)
            word = afterX(word + 'x');
        else if (db < y)
            word = secVowel(word + "y");
        else
            word = afterZ(word + "z");
        return word;
    }

    private String afterB(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.1)
            word = secVowel(word + 'l');
        else if (db < 0.3)
            word = secVowel(word + 'r');
        else if (db < 0.35)
            word = afterD(word + 'd');
        else if (db < 0.7) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.85)
                word = afterB(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.53)
                    word = secVowel(word + 'b');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterC(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5 && isVowel(word.charAt(word.length() - 2)))
            return word + 'k';
        else if (db < 0.07)
            word = secVowel(word + 'l');
        else if (db < 0.2)
            word = secVowel(word + 'r');
        else if (db < 0.3)
            word = secVowel(word + 'h');
        else if (db < 0.32)
            word = secVowel(word + 'n');
        else if (db < 0.33)
            word = secVowel(word + 'm');
        else if (db < 0.35)
            word = secVowel(word + 't');
        else if (db < 0.37)
            word = afterT(word + 't');
        else if (db < 0.45)
            word = afterA2(word + 'a');
        else if (db < 0.52)
            word = afterO2(word + 'o');
        else if (db < 0.56)
            word = afterU2(word + 'u');
        else if (word.length() < 3)
            word = word + 'k';
        else if (word.length() > 6 && db < 0.75)
            word = afterC(word);
        else if (isVowel(word.charAt(word.length() - 2)) &&
                !isVowel(word.charAt(word.length() - 3)))
            word = word + 'k';
        else
            word = word.substring(0, word.length() - 1) + 'k';
        return word;
    }

    private String afterD(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.03)
            word = secVowel(word + 'l');
        else if (db < 0.1)
            word = secVowel(word + 'r');
        else if (db < 0.12)
            word = secVowel(word + 'w');
        else if (db < 0.16)
            word = secVowel(word + 'z');
        else if (db < 0.18)
            word = word + 'z';
        else if (db < 0.21)
            word = secVowel(word + 'j');
        else if (db < 0.24)
            word = word + 'j';
        else if (db < 0.5) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.8)
                word = afterD(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.39)
                    word = secVowel(word + 'd');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterF(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.03)
            word = secVowel(word + 'l');
        else if (db < 0.07)
            word = secVowel(word + 'r');
        else if (db < 0.12)
            word = secVowel(word + 's');
        else if (db < 0.16)
            word = word + 's';
        else if (db < 0.19)
            word = secVowel(word + 's');
        else if (db < 0.21)
            word = secVowel(word + 't');
        else if (db < 0.22)
            word = word + 't';
        else if (db < 0.75) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.85)
                word = afterF(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.48)
                    word = secVowel(word + 'f');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterG(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.04)
            word = secVowel(word + 'l');
        else if (db < 0.15)
            word = secVowel(word + 'r');
        else if (db < 0.17)
            word = secVowel(word + 'w');
        else if (db < 0.20)
            word = secVowel(word + 'z');
        else if (db < 0.23)
            word = word + 'z';
        else if (db < 0.27)
            word = secVowel(word + 'd');
        else if (db < 0.28)
            word = secVowel(word + 'm');
        else if (db < 0.31)
            word = secVowel(word + 'n');
        else if (db < 0.34)
            word = word + 'd';
        else if (db < 0.75) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.85)
                word = afterG(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.54)
                    word = secVowel(word + 'g');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterK(String word) {
        double db = rand.nextDouble();
        if (db < 0.005)
            word = word + 'y';
        else if (db < 0.5)
            word = afterE2(word + 'e');
        else
            word = afterI2(word + 'i');
        return word;
    }

    private String afterL(String word) {
        double t = 0.1;
        double d = t + 0.08;
        double n = d + 0.02;
        double m = n + 0.02;
        double b = m + 0.02;
        double p = b + 0.02;
        double s = p + 0.04;
        double z = s + 0.01;
        double g = z + 0.03;
        double c = g + 0.04;
        double k = g + 0.02;
        double q = k + 0.005;
        double h = q + 0.005;
        double r = h + 0.014;
        double y = r + 0.003;
        double w = y + 0.003;
        double cont1 = y + 0.12;
        double cont = cont1 + 0.13;
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < t)
            word = afterT(word + 't');
        else if (db < d)
            word = afterD(word + 'd');
        else if (db < n)
            word = secVowel(word + 'n');
        else if (db < m)
            word = secVowel(word + 'm');
        else if (db < b)
            word = afterB(word + 'b');
        else if (db < p)
            word = afterP(word + 'p');
        else if (db < s)
            word = afterS(word + 's');
        else if (db < z)
            word = afterZ(word + 'z');
        else if (db < g)
            word = afterG(word + 'g');
        else if (db < c)
            word = afterC(word + 'c');
        else if (db < k)
            word = afterK(word + 'k');
        else if (db < q)
            word = afterQ(word + "qu");
        else if (db < h)
            word = secVowel(word + 'h');
        else if (db < r)
            word = secVowel(word + 'r');
        else if (db < y)
            word = secVowel(word + 'y');
        else if (db < w)
            word = secVowel(word + 'w');
        else if (db < cont) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < cont + 0.15)
                word = afterL(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < cont1)
                    word = secVowel(word + 'l');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        } else if (word.length() < 3 ||
                isVowel(word.charAt(word.length() - 2)) &&
                        !isVowel(word.charAt(word.length() - 3))) {
            if (db < cont + 0.1)
                word = word + 'l';
        }
        return word;
    }

    private String afterM(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.01)
            word = word + 's';
        else if (db < 0.03)
            word = word + 'b';
        else if (db < 0.05)
            word = word + 'p';
        else if (db < 0.06)
            word = word + 'd';
        else if (db < 0.075)
            word = secVowel(word + 'l');
        else if (db < 0.09)
            word = secVowel(word + 'r');
        else if (db < 0.14)
            word = afterB(word + 'b');
        else if (db < 0.19)
            word = afterP(word + 'p');
        else if (db < 0.21)
            word = afterD(word + 'd');
        else if (db < 0.23)
            word = afterS(word + 's');
        else if (db < 0.24)
            word = secVowel(word + 'j');
        else if (db < 0.26)
            word = secVowel(word + 'n');
        else if (db < 0.28)
            word = secVowel(word + 'z');
        else if (db < 0.6) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.8)
                word = afterM(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.44)
                    word = secVowel(word + 'm');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterN(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.05)
            word = word + 'g';
        else if (db < 0.08)
            word = secVowel(word + 'g');
        else if (db < 0.09)
            word = secVowel(word + "gr");
        else if (db < 0.1)
            word = secVowel(word + "gl");
        else if (db < 0.11)
            word = secVowel(word + 'y');
        else if (db < 0.19)
            word = word + 't';
        else if (db < 0.24)
            word = word + 'd';
        else if (db < 0.32)
            word = afterT(word + 't');
        else if (db < 0.36)
            word = afterD(word + 'd');
        else if (db < 0.38)
            word = secVowel(word + 'r');
        else if (db < 0.39)
            word = secVowel(word + 'l');
        else if (db < 0.41)
            word = word + 's';
        else if (db < 0.43)
            word = afterS(word + 's');
        else if (db < 0.45)
            word = word + 'k';
        else if (db < 0.46)
            word = afterK(word + 'k');
        else if (db < 0.47)
            word = afterC(word + 'c');
        else if (db < 0.475)
            word = word + "ch";
        else if (db < 0.477)
            word = secVowel(word + "ch");
        else if (db < 0.48)
            word = secVowel(word + 'h');
        else if (db < 0.49)
            word = secVowel(word + 'm');
        else if (db < 0.7) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.85)
                word = afterN(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.59)
                    word = secVowel(word + 'n');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterP(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.1)
            word = secVowel(word + 'l');
        else if (db < 0.3)
            word = secVowel(word + 'r');
        else if (db < 0.35)
            word = afterS(word + 's');
        else if (db < 0.37)
            word = afterT(word + 't');
        else if (db < 0.375)
            word = secVowel(word + 'n');
        else if (db < 0.7) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.7)
                word = afterP(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.53)
                    word = secVowel(word + 'p');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterQ(String word) {
        double db = rand.nextDouble();
        if (db < 0.375)
            word = afterA2(word + 'a');
        else if (db < 0.5)
            word = afterE2(word + 'e');
        else if (db < 0.875)
            word = afterI2(word + 'i');
        else
            word = afterO2(word + 'o');
        return word;
    }

    private String afterR(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.002)
            word = word + 'b';
        else if (db < 0.007)
            word = secVowel(word + 'b');
        else if (db < 0.01)
            word = afterB(word + 'b');
        else if (db < 0.02)
            word = word + 'k';
        else if (db < 0.03)
            word = afterK(word + 'k');
        else if (db < 0.034)
            word = afterA2(word + "ca");
        else if (db < 0.036)
            word = afterO2(word + "co");
        else if (db < 0.039)
            word = afterU2(word + "cu");
        else if (db < 0.041)
            word = secVowel(word + "cl");
        else if (db < 0.043)
            word = secVowel(word + "cr");
        else if (db < 0.055)
            word = word + 'd';
        else if (db < 0.06)
            word = afterD(word + 'd');
        else if (db < 0.063)
            word = word + 'f';
        else if (db < 0.07)
            word = afterF(word + 'f');
        else if (db < 0.072)
            word = word + 'g';
        else if (db < 0.075)
            word = afterG(word + 'g');
        else if (db < 0.08)
            word = secVowel(word + 'h');
        else if (db < 0.082)
            word = word + 'j';
        else if (db < 0.085)
            word = secVowel(word + 'j');
        else if (db < 0.10)
            word = secVowel(word + 'l');
        else if (db < 0.105)
            word = word + 'l';
        else if (db < 0.11)
            word = afterL(word + 'l');
        else if (db < 0.12)
            word = word + 'm';
        else if (db < 0.13)
            word = secVowel(word + 'm');
        else if (db < 0.14)
            word = word + 'n';
        else if (db < 0.15)
            word = secVowel(word + 'n');
        else if (db < 0.155)
            word = word + 'p';
        else if (db < 0.12)
            word = afterP(word + 'p');
        else if (db < 0.123)
            word = afterQ(word + "qu");
        else if (db < 0.14)
            word = afterS(word + 's');
        else if (db < 0.17)
            word = afterT(word + 't');
        else if (db < 0.18)
            word = secVowel(word + 'v');
        else if (db < 0.185)
            word = secVowel(word + 'w');
        else if (db < 0.188)
            word = afterX(word + 'x');
        else if (db < 0.19)
            word = secVowel(word + 'y');
        else if (db < 0.195)
            word = afterZ(word + 'z');
        else if (db < 0.45) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.7)
                word = afterR(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.25)
                    word = secVowel(word + 'r');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterS(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (word.length() > 7 && rand.nextDouble() < 0.2)
            return word + 'h';
        else if (word.length() > 2 && !isVowel(word.charAt(word.length() - 2)) && rand.nextDouble() < 0.7)
            return word;
        else if (db < 0.02)
            word = word + 'k';
        else if (db < 0.027)
            word = secVowel(word + "cr");
        else if (db < 0.03)
            word = secVowel(word + "cl");
        else if (db < 0.06)
            word = word + "h";
        else if (db < 0.061)
            word = secVowel(word + "hm");
        else if (db < 0.062)
            word = secVowel(word + "hn");
        else if (db < 0.063)
            word = secVowel(word + "ht");
        else if (db < 0.067)
            word = secVowel(word + "hl");
        else if (db < 0.069)
            word = secVowel(word + "hr");
        else if (db < 0.07)
            word = secVowel(word + "hw");
        else if (db < 0.08)
            word = secVowel(word + 'l');
        else if (db < 0.09)
            word = secVowel(word + 'm');
        else if (db < 0.10)
            word = secVowel(word + 'n');
        else if (db < 0.105)
            word = word + 'p';
        else if (db < 0.112)
            word = secVowel(word + 'p');
        else if (db < 0.115)
            word = secVowel(word + "pr");
        else if (db < 0.117)
            word = secVowel(word + "pl");
        else if (db < 0.12)
            word = afterQ(word + "qu");
        else if (db < 0.13)
            word = afterK(word + "k");
        else if (db < 0.135)
            word = afterA2(word + "ca");
        else if (db < 0.139)
            word = afterO2(word + "co");
        else if (db < 0.141)
            word = afterU2(word + "cu");
        else if (db < 0.144)
            word = secVowel(word + 'r');
        else if (db < 0.18)
            word = word + 't';
        else if (db < 0.21)
            word = secVowel(word + 't');
        else if (db < 0.22)
            word = secVowel(word + "tr");
        else if (db < 0.222)
            word = secVowel(word + "tl");
        else if (db < 0.23)
            word = secVowel(word + 'w');
        else if (db < 0.5) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.8)
                word = afterS(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.37)
                    word = secVowel(word + 's');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        } else if (word.length() < 3 ||
                isVowel(word.charAt(word.length() - 2)) &&
                        !isVowel(word.charAt(word.length() - 3))) {
            if (db < 0.75)
                word = word + 's';
        }
        return word;
    }

    private String afterT(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (word.length() > 7 && rand.nextDouble() < 0.3)
            return word + 'h';
        else if (db < 0.1)
            word = word + 'h';
        else if (db < 0.12)
            word = secVowel(word + "hr");
        else if (db < 0.13)
            word = secVowel(word + "hl");
        else if (db < 0.14)
            word = secVowel(word + "hw");
        else if (db < 0.2)
            word = secVowel(word + 'h');
        else if (db < 0.24)
            word = word + 's';
        else if (db < 0.29)
            word = secVowel(word + 's');
        else if (db < 0.3)
            word = secVowel(word + "st");
        else if (db < 0.34)
            word = secVowel(word + 'r');
        else if (db < 0.36)
            word = secVowel(word + 'l');
        else if (db < 0.4)
            word = word + "ch";
        else if (db < 0.42)
            word = secVowel(word + "ch");
        else if (db < 0.43)
            word = secVowel(word + 'w');
        else if (db < 0.432)
            word = afterK(word + "sk");
        else if (db < 0.434)
            word = afterA2(word + "sca");
        else if (db < 0.436)
            word = afterO2(word + "sco");
        else if (db < 0.437)
            word = afterU2(word + "scu");
        else if (db < 0.439)
            word = secVowel(word + "scr");
        else if (db < 0.44)
            word = secVowel(word + "sp");
        else if (db < 0.441)
            word = secVowel(word + "spr");
        else if (db < 0.442)
            word = secVowel(word + 'p');
        else if (db < 0.443)
            word = secVowel(word + 'b');
        else if (db < 0.45)
            word = secVowel(word + 'n');
        else if (db < 0.65) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.8)
                word = afterT(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.55)
                    word = secVowel(word + 't');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String afterV(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.3)
            return word;
        else if (db < 0.01)
            word = secVowel(word + 'l');
        else if (db < 0.02)
            word = secVowel(word + 'r');
        else if (db < 0.03)
            word = secVowel(word + 's');
        else if (db < 0.04)
            word = secVowel(word + 'n');
        else if (db < 0.05)
            word = secVowel(word + 'y');
        else if (db < 0.06)
            word = afterD(word + 'd');
        else if (db < 0.85)
            word = secVowel(word);
        return word;
    }

    private String afterX(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.01)
            word = secVowel(word + 'l');
        else if (db < 0.04)
            word = secVowel(word + 't');
        else if (db < 0.05)
            word = secVowel(word + 'n');
        else if (db < 0.06)
            word = secVowel(word + 'r');
        else if (db < 0.065)
            word = secVowel(word + "tr");
        else if (db < 0.07)
            word = afterK(word + 'k');
        else if (db < 0.075)
            word = afterC(word + 'c');
        else if (db < 0.08)
            word = afterP(word + 'p');
        else if (db < 0.48)
            word = secVowel(word);
        return word;
    }

    private String afterZ(String word) {
        double db = rand.nextDouble();
        if (word.length() > 7 && rand.nextDouble() < 0.5)
            return word;
        else if (db < 0.02)
            word = secVowel(word + 'l');
        else if (db < 0.03)
            word = afterG(word + 'g');
        else if (db < 0.04)
            word = afterD(word + 'd');
        else if (db < 0.045)
            word = afterB(word + 'b');
        else if (db < 0.05)
            word = secVowel(word + 'y');
        else if (db < 0.06)
            word = secVowel(word + 'n');
        else if (db < 0.065)
            word = secVowel(word + 'm');
        else if (db < 0.07)
            word = secVowel(word + 'r');
        else if (db < 0.08)
            word = secVowel(word + 'w');
        else if (db < 0.6) {
            if (word.length() < 3)
                word = secVowel(word);
            else if (word.length() > 6 && db < 0.8)
                word = afterZ(word);
            else if (isVowel(word.charAt(word.length() - 2)) &&
                    !isVowel(word.charAt(word.length() - 3)))
                if (db < 0.34)
                    word = secVowel(word + 'z');
                else
                    word = secVowel(word);
            else
                word = secVowel(word);
        }
        return word;
    }

    private String secVowel(String word) {
        double a = 0.28;
        double e = a + 0.18;
        double i = e + 0.08;
        double y = i + 0.1;
        double o = y + 0.23;
        double db = rand.nextDouble();
        if (db < a)
            word = afterA2(word + "a");
        else if (db < e)
            word = afterE2(word + "e");
        else if (db < i)
            word = afterI2(word + "i");
        else if (db < y)
            if (word.charAt(word.length() - 1) == 'y')
                word = word + 'i';
            else
                word = word + 'y';
        else if (db < o)
            word = afterO2(word + "o");
        else
            word = afterU2(word + "u");
        return word;
    }

    private String afterA2(String word) {
        double db = rand.nextDouble();
        if (db < 0.3)
            return word;
        db = rand.nextDouble();
        if (db < 0.02)
            word = afterVowel2(word + 'i');
        else if (db < 0.035)
            word = afterVowel2(word + 'u');
        else if (db < 0.055)
            word = afterVowel2(word + 'e');
        else if (db < 0.06)
            word = afterVowel2(word + 'o');
        else
            word = afterVowel2(word);
        return word;
    }

    private String afterE2(String word) {
        double db = rand.nextDouble();
        if (db < 0.2)
            return word;
        db = rand.nextDouble();
        if (db < 0.02)
            word = afterVowel2(word + 'a');
        else if (db < 0.04)
            word = afterVowel2(word + 'e');
        else if (db < 0.045)
            word = afterVowel2(word + 'o');
        else if (db < 0.05)
            word = afterVowel2(word + 'u');
        else if (db < 0.06)
            word = afterVowel2(word + 'i');
        else
            word = afterVowel2(word);
        return word;
    }

    private String afterI2(String word) {
        double db = rand.nextDouble();
        if (db < 0.025)
            word = afterVowel2(word + 'a');
        else if (db < 0.04)
            word = afterVowel2(word + 'e');
        else if (db < 0.07)
            word = afterVowel2(word + 'o');
        else if (db < 0.1)
            word = afterVowel2(word + 'u');
        else
            word = afterVowel2(word);
        return word;
    }

    private String afterO2(String word) {
        double db = rand.nextDouble();
        if (db < 0.25)
            return word;
        db = rand.nextDouble();
        if (db < 0.02)
            word = afterVowel2(word + 'a');
        else if (db < 0.05)
            word = afterVowel2(word + 'i');
        else if (db < 0.07)
            word = afterVowel2(word + 'o');
        else if (db < 0.097)
            word = afterVowel2(word + 'u');
        else if (db < 0.1)
            word = afterVowel2(word + 'e');
        else
            word = afterVowel2(word);
        return word;
    }

    private String afterU2(String word) {
        double db = rand.nextDouble();
        if (db < 0.2)
            return word;
        db = rand.nextDouble();
        if (db < 0.02)
            word = afterVowel2(word + 'a');
        else if (db < 0.03)
            word = afterVowel2(word + 'e');
        else if (db < 0.045)
            word = afterVowel2(word + 'o');
        else if (db < 0.065)
            word = afterVowel2(word + 'i');
        else
            word = afterVowel2(word);
        return word;
    }

    private String afterVowel2(String word) {
        double db = rand.nextDouble();
        if (db < 0.1 || word.length() > 6 && db < 0.5)
            if (word.charAt(word.length() - 1) == 'i' &&
                    !isVowel(word.charAt(word.length() - 2)))
                return word.substring(0, word.length() - 1) + 'y';
            else
                return word;
        else
            return afterVowel(word);
    }
}
