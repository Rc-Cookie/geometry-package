package com.github.rccookie.geometry.performance;

public final class FastMath {

    private FastMath() {
        throw new UnsupportedOperationException();
    }


    /**
     * Constant by which to multiply an angular value in degrees to obtain an
     * angular value in radians.
     */
    public static final float DEGREES_TO_RADIANS = 0.017453292519943295f;

    /**
     * Constant by which to multiply an angular value in radians to obtain an
     * angular value in degrees.
     */
    public static final float RADIANS_TO_DEGREES = 57.29577951308232f;

    public static final float PI = (float) Math.PI;


    private static final int SIN_PRECISION = 100;
    private static final int MODULUS = 360 * SIN_PRECISION;
    private static final float[] SIN = new float[MODULUS];

    static {
        for (int i = 0; i< SIN.length; i++)
            SIN[i] = (float) Math.sin((i * Math.PI) / (SIN_PRECISION * 180));
    }



    private static float sinLookup(int a) {
        return a >= 0 ? SIN[a >= MODULUS ? a % MODULUS : a] : -SIN[-a >= MODULUS ? -a % MODULUS : -a];
    }

    public static float sin(float a) {
        return sinLookup((int)(a * SIN_PRECISION + 0.5));
    }

    public static float cos(float a) {
        return sinLookup((int)((a + 90) * SIN_PRECISION + 0.5));
    }



    public static float clamp(float x, float l, float h) {
        //noinspection ManualMinMaxCalculation
        return x < l ? l : x > h ? h : x;
    }

    public static int clamp(int x, int l, int h) {
        //noinspection ManualMinMaxCalculation
        return x < l ? l : x > h ? h : x;
    }



    private static final int Size_Ac = 100000;

    private static final float[] Atan2 = new float[Size_Ac + 1];
//    private static final float[] Atan2_PM = new float[Size_Ac + 1];
//    private static final float[] Atan2_MP = new float[Size_Ac + 1];
//    private static final float[] Atan2_MM = new float[Size_Ac + 1];
//
//    private static final float[] Atan2_R = new float[Size_Ac + 1];
//    private static final float[] Atan2_RPM = new float[Size_Ac + 1];
//    private static final float[] Atan2_RMP = new float[Size_Ac + 1];
//    private static final float[] Atan2_RMM = new float[Size_Ac + 1];

    static {
        for (int i = 0; i <= Size_Ac; i++) {
            float d = (float) i / Size_Ac;
            float x = 1;
            float y = x * d;
            float v = (float) Math.atan2(y, x) * RADIANS_TO_DEGREES;
            Atan2[i] = v;
//            Atan2_PM[i] = 360 - v;
//            Atan2_MP[i] = -v;
//            Atan2_MM[i] = -360 + v;
//
//            Atan2_R[i] = 180 - v;
//            Atan2_RPM[i] = 90 + v;
//            Atan2_RMP[i] = -180 + v;
//            Atan2_RMM[i] = -180 - v;
        }
    }

    public static float atan2(float y, float x) {
        if (y < 0) {
            if (x < 0)
                return y < x ?
                        Atan2/*_RMM*/[(int) ((1 - x / y) * Size_Ac)] - 135 :
                        Atan2/*_MM*/[(int) (y / x * Size_Ac)] - 180;
            y = -y;
            return y > x ?
                    Atan2/*_RMP*/[(int) (x / y * Size_Ac)] - 90 :
                    Atan2/*_MP*/[(int) ((1 - y / x) * Size_Ac)] - 45;
        }
        if (x < 0) {
            x = -x;
            return y > x ?
                    Atan2/*_RPM*/[(int) (x / y * Size_Ac)] + 90 :
                    Atan2/*_PM*/[(int) ((1 - y / x) * Size_Ac)] + 135;
        }
        return y > x ?
                Atan2/*_R*/[(int) ((1 - x / y) * Size_Ac)] + 45 :
                Atan2[(int) (y / x * Size_Ac)];
    }



    private static final float ASIN_MAX_VALUE_FOR_TABS = (float) StrictMath.sin(StrictMath.toRadians(73.0));
    static final int ASIN_TABS_SIZE = 8193;
    static final float ASIN_DELTA = ASIN_MAX_VALUE_FOR_TABS/(ASIN_TABS_SIZE - 1);
    static final float ASIN_INDEXER = 1/ASIN_DELTA;

    static final float ONE_DIV_F2 = 1/2f;
    static final float ONE_DIV_F3 = 1/6f;
    static final float ONE_DIV_F4 = 1/24f;

    static final class MyTAsin {
        static final float[] asinTab = new float[ASIN_TABS_SIZE];
        static final float[] asinDer1DivF1Tab = new float[ASIN_TABS_SIZE];
        static final float[] asinDer2DivF2Tab = new float[ASIN_TABS_SIZE];
        static final float[] asinDer3DivF3Tab = new float[ASIN_TABS_SIZE];
        static final float[] asinDer4DivF4Tab = new float[ASIN_TABS_SIZE];
        static {
            init();
        }
        private static strictfp void init() {
            for (int i=0;i<ASIN_TABS_SIZE;i++) {
                // x: in [0,ASIN_MAX_VALUE_FOR_TABS].
                float x = i * ASIN_DELTA;
                float oneMinusXSqInv = 1/(1-x*x);
                float oneMinusXSqInv0_5 = (float) StrictMath.sqrt(oneMinusXSqInv);
                float oneMinusXSqInv1_5 = oneMinusXSqInv0_5*oneMinusXSqInv;
                float oneMinusXSqInv2_5 = oneMinusXSqInv1_5*oneMinusXSqInv;
                float oneMinusXSqInv3_5 = oneMinusXSqInv2_5*oneMinusXSqInv;
                asinTab[i] = (float) StrictMath.asin(x);
                asinDer1DivF1Tab[i] = oneMinusXSqInv0_5;
                asinDer2DivF2Tab[i] = (x*oneMinusXSqInv1_5) * ONE_DIV_F2;
                asinDer3DivF3Tab[i] = ((1+2*x*x)*oneMinusXSqInv2_5) * ONE_DIV_F3;
                asinDer4DivF4Tab[i] = ((5+2*x*(2+x*(5-2*x)))*oneMinusXSqInv3_5) * ONE_DIV_F4;
            }
        }
    }

    static final float ASIN_MAX_VALUE_FOR_POWTABS = (float) StrictMath.sin(StrictMath.toRadians(88.6));
    static final int ASIN_POWTABS_POWER = 84;

    static final float ASIN_POWTABS_ONE_DIV_MAX_VALUE = 1/ASIN_MAX_VALUE_FOR_POWTABS;
    static final int ASIN_POWTABS_SIZE = /*(FM_USE_POWTABS_FOR_ASIN || SFM_USE_POWTABS_FOR_ASIN) ? (1<<12) + 1 : */0;
    static final int ASIN_POWTABS_SIZE_MINUS_ONE = ASIN_POWTABS_SIZE - 1;

    static final class MyTAsinPow {
        static final float[] asinParamPowTab = new float[ASIN_POWTABS_SIZE];
        static final float[] asinPowTab = new float[ASIN_POWTABS_SIZE];
        static final float[] asinDer1DivF1PowTab = new float[ASIN_POWTABS_SIZE];
        static final float[] asinDer2DivF2PowTab = new float[ASIN_POWTABS_SIZE];
        static final float[] asinDer3DivF3PowTab = new float[ASIN_POWTABS_SIZE];
        static final float[] asinDer4DivF4PowTab = new float[ASIN_POWTABS_SIZE];
        static {
            init();
        }
        private static strictfp void init() {
//            if (FM_USE_POWTABS_FOR_ASIN || SFM_USE_POWTABS_FOR_ASIN) {
//                for (int i=0;i<ASIN_POWTABS_SIZE;i++) {
//                    // x: in [0,ASIN_MAX_VALUE_FOR_POWTABS].
//                    float x = StrictMath.pow(i*(1.0/ASIN_POWTABS_SIZE_MINUS_ONE), 1.0/ASIN_POWTABS_POWER) * ASIN_MAX_VALUE_FOR_POWTABS;
//                    float oneMinusXSqInv = 1/(1-x*x);
//                    float oneMinusXSqInv0_5 = StrictMath.sqrt(oneMinusXSqInv);
//                    float oneMinusXSqInv1_5 = oneMinusXSqInv0_5*oneMinusXSqInv;
//                    float oneMinusXSqInv2_5 = oneMinusXSqInv1_5*oneMinusXSqInv;
//                    float oneMinusXSqInv3_5 = oneMinusXSqInv2_5*oneMinusXSqInv;
//                    asinParamPowTab[i] = x;
//                    asinPowTab[i] = StrictMath.asin(x);
//                    asinDer1DivF1PowTab[i] = oneMinusXSqInv0_5;
//                    asinDer2DivF2PowTab[i] = (x*oneMinusXSqInv1_5) * ONE_DIV_F2;
//                    asinDer3DivF3PowTab[i] = ((1+2*x*x)*oneMinusXSqInv2_5) * ONE_DIV_F3;
//                    asinDer4DivF4PowTab[i] = ((5+2*x*(2+x*(5-2*x)))*oneMinusXSqInv3_5) * ONE_DIV_F4;
//                }
//            }
        }
    }

    static final float ASIN_PIO2_HI = (float) Double.longBitsToDouble(0x3FF921FB54442D18L); // 1.57079632679489655800e+00
    static final float ASIN_PIO2_LO = (float) Double.longBitsToDouble(0x3C91A62633145C07L); // 6.12323399573676603587e-17
    static final float ASIN_PS0     = (float) Double.longBitsToDouble(0x3fc5555555555555L); //  1.66666666666666657415e-01
    static final float ASIN_PS1     = (float) Double.longBitsToDouble(0xbfd4d61203eb6f7dL); // -3.25565818622400915405e-01
    static final float ASIN_PS2     = (float) Double.longBitsToDouble(0x3fc9c1550e884455L); //  2.01212532134862925881e-01
    static final float ASIN_PS3     = (float) Double.longBitsToDouble(0xbfa48228b5688f3bL); // -4.00555345006794114027e-02
    static final float ASIN_PS4     = (float) Double.longBitsToDouble(0x3f49efe07501b288L); //  7.91534994289814532176e-04
    static final float ASIN_PS5     = (float) Double.longBitsToDouble(0x3f023de10dfdf709L); //  3.47933107596021167570e-05
    static final float ASIN_QS1     = (float) Double.longBitsToDouble(0xc0033a271c8a2d4bL); // -2.40339491173441421878e+00
    static final float ASIN_QS2     = (float) Double.longBitsToDouble(0x40002ae59c598ac8L); //  2.02094576023350569471e+00
    static final float ASIN_QS3     = (float) Double.longBitsToDouble(0xbfe6066c1b8d0159L); // -6.88283971605453293030e-01
    static final float ASIN_QS4     = (float) Double.longBitsToDouble(0x3fb3b8c5b12e9282L); //  7.70381505559019352791e-02

    /**
     * @param value In range [-1; 1]
     */
    public static float asin(float value) {
        boolean negateResult = false;
        if (value < 0.0) {
            value = -value;
            negateResult = true;
        }
        if (value <= ASIN_MAX_VALUE_FOR_TABS) {
            int index = (int)(value * ASIN_INDEXER + 0.5);
            float delta = value - index * ASIN_DELTA;
            float result = MyTAsin.asinTab[index]
                    + delta * (MyTAsin.asinDer1DivF1Tab[index]
                    + delta * (MyTAsin.asinDer2DivF2Tab[index]
                    + delta * (MyTAsin.asinDer3DivF3Tab[index]
                    + delta * MyTAsin.asinDer4DivF4Tab[index])));
            result *= RADIANS_TO_DEGREES;
            return negateResult ? -result : result;
        } /*else if (USE_POWTABS_FOR_ASIN && (value <= ASIN_MAX_VALUE_FOR_POWTABS)) {
            int index = (int)(powFast(value * ASIN_POWTABS_ONE_DIV_MAX_VALUE, ASIN_POWTABS_POWER) * ASIN_POWTABS_SIZE_MINUS_ONE + 0.5);
            float delta = value - MyTAsinPow.asinParamPowTab[index];
            float result = MyTAsinPow.asinPowTab[index]
                    + delta * (MyTAsinPow.asinDer1DivF1PowTab[index]
                    + delta * (MyTAsinPow.asinDer2DivF2PowTab[index]
                    + delta * (MyTAsinPow.asinDer3DivF3PowTab[index]
                    + delta * MyTAsinPow.asinDer4DivF4PowTab[index])));
            return negateResult ? -result : result;
        }*/ else { // value > ASIN_MAX_VALUE_FOR_TABS, or value is NaN
            // This part is derived from fdlibm.
            if (value < 1.0) {
                float t = (1f - value)*0.5f;
                float p = t*(ASIN_PS0+t*(ASIN_PS1+t*(ASIN_PS2+t*(ASIN_PS3+t*(ASIN_PS4+t*ASIN_PS5)))));
                float q = 1f+t*(ASIN_QS1+t*(ASIN_QS2+t*(ASIN_QS3+t*ASIN_QS4)));
                float s = (float) Math.sqrt(t);
                float z = s+s*(p/q);
                float result = (ASIN_PIO2_HI-((z+z)-ASIN_PIO2_LO)) * RADIANS_TO_DEGREES;
                return negateResult ? -result : result;
            } else { // value >= 1.0, or value is NaN
                if (value == 1.0) {
                    return negateResult ? -90 : 90;
                } else {
                    return Float.NaN;
                }
            }
        }
    }

    /**
     * @param value In range [-1; 1]
     */
    public static float acos(float value) {
        return 90 - asin(value);
    }
}
