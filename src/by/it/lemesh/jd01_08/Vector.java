package by.it.lemesh.jd01_08;

import java.util.Arrays;

class Vector extends Var {
    private double[] value;

    public double[] getValue() {
        return value;
    }

    @Override
    public Var add(Var other) {
        if (other instanceof Scalar) {
            double[] sum = Arrays.copyOf(value, value.length);
            for (int i = 0; i < sum.length; i++) {
                sum[i] = sum[i] + ((Scalar) other).getValue();
            }
            return new Vector(sum);
        } else if ((other instanceof Vector) & (value.length == ((Vector) other).value.length)) {
            double[] sum = Arrays.copyOf(value, value.length);
            for (int i = 0; i < sum.length; i++) {
                sum[i] = sum[i] + ((Vector) other).value[i];
            }
            return new Vector(sum);
        } else return super.add(other);
    }

    @Override
    public Var sub(Var other) {
        if (other instanceof Scalar) {
            double[] sub = Arrays.copyOf(value, value.length);
            for (int i = 0; i < sub.length; i++) {
                sub[i] = sub[i] - ((Scalar) other).getValue();
            }
            return new Vector(sub);
        } else if ((other instanceof Vector) & (value.length == ((Vector) other).value.length)) {
            double[] sub = Arrays.copyOf(value, value.length);
            for (int i = 0; i < sub.length; i++) {
                sub[i] = sub[i] - ((Vector) other).value[i];
            }
            return new Vector(sub);
        } else return super.sub(other);
    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Scalar) {
            double[] mul = Arrays.copyOf(value, value.length);
            for (int i = 0; i < mul.length; i++) {
                mul[i] = mul[i] * ((Scalar) other).getValue();
            }
            return new Vector(mul);
        } else if ((other instanceof Vector) & (value.length == ((Vector) other).value.length)) {
            double[] mul = Arrays.copyOf(value, value.length);
            double mulSum = 0;
            for (int i = 0; i < mul.length; i++) {
                mul[i] = mul[i] * ((Vector) other).value[i];
                mulSum += mul[i];
            }
            return new Scalar(mulSum);
        } else return super.mul(other);
    }

    @Override
    public Var div(Var other) {
        if ((other instanceof Scalar) && (((Scalar) other).getValue() != 0)) {
            double[] div = Arrays.copyOf(value, value.length);
            for (int i = 0; i < div.length; i++) {
                div[i] = div[i] / ((Scalar) other).getValue();
            }
            return new Vector(div);
        } else return super.div(other);
    }

    Vector(double[] value) {
        this.value = value;
    }

    Vector(String strValue) {
        strValue = strValue.replaceAll("}", "");
        strValue = strValue.replace('{', ' ').trim();
        String[] str = strValue.split(",");
        this.value = new double[str.length];
        for (int i = 0; i < str.length; i++) {
            this.value[i] = Double.parseDouble(str[i]);
        }
    }

    Vector(Vector vector) {
        this.value = Arrays.copyOf(vector.value, vector.value.length);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String s = "";
        for (int i = 0; i < value.length; i++) {
            sb.append(s).append(value[i]);
            s = ", ";
        }
        sb.append("}");
        return sb.toString();
    }
}