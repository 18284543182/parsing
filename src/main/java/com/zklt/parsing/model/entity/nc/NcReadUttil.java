package com.zklt.parsing.model.entity.nc;

import cn.hutool.core.util.NumberUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ucar.ma2.Array;
import ucar.nc2.Attribute;
import ucar.nc2.NetcdfFile;
import ucar.nc2.Variable;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * nc工具类
 * @author xiejian
 * @date 2023-1-11
 */
public class NcReadUttil {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NcResult {
        private Map<String, Object> vals;
        private List<Attr> attrs;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Scale {
        private Double scaleFactory;
        private Double addOffset = null;
        private Double fillValue = null;
    }

    public static NcResult read(List<String> shortNames, String url) throws IOException {
        NetcdfFile dataFile = null;
        try {
            dataFile = NetcdfFile.open(url,
                    null);
            return read(dataFile, shortNames);
        } catch (Exception e) {
            e.printStackTrace();
            if (dataFile != null) {
                dataFile.close();
            }
            throw e;
        }
    }

    private static NcResult read(NetcdfFile dataFile, List<String> shortNames) throws IOException {
        List<Variable> variables = dataFile.getVariables();
        Map<String, Object> name2Val = Maps.newHashMap();
        List<Attr> attrs = getAttrs(dataFile);
        for (Variable variable : variables) {
            String shortName = variable.getShortName();
            if (!shortNames.contains(shortName)) {
                continue;
            }
            Array array = variable.read();
            Object o = array.copyToNDJavaArray();
            Scale scale = loadScale(variable);
            if (scale.getScaleFactory() != null || scale.getFillValue() != null) {
                Object convertValue = calc(o, scale.getScaleFactory(),
                        scale.getFillValue(), scale.getAddOffset());
                name2Val.put(shortName, convertValue);
            } else {
                name2Val.put(shortName, o);
            }
        }
        return new NcResult(name2Val, attrs);
    }

    private static Object calc(Object o, Double scaleFactory, Double fillValue,
                               Double addOffset) {
        if (addOffset == null) {
            addOffset = 0.0;
        }
        if (scaleFactory == null) {
            scaleFactory = 1.0;
        }
        if (o instanceof short[]) {
            short[] value = (short[]) o;
            Float[] convertValue = new Float[value.length];
            for (int i = 0; i < value.length; i++) {
                if (fillValue != null &&
                        NumberUtil.sub(fillValue.doubleValue(), value[i]) == 0) {
                    continue;
                }
                convertValue[i] = (float) (value[i] * scaleFactory + addOffset);
            }
            return convertValue;
        } else if (o instanceof int[]) {
            int[] value = (int[]) o;
            Float[] convertValue = new Float[value.length];
            for (int i = 0; i < value.length; i++) {
                if (fillValue != null
                        && NumberUtil.sub(fillValue.doubleValue(), value[i]) == 0) {
                    continue;
                }
                convertValue[i] = (float) (value[i] * scaleFactory + addOffset);
            }
            return convertValue;
        } else if (o instanceof float[]) {
            float[] value = (float[]) o;
            Float[] convertValue = new Float[value.length];
            for (int i = 0; i < value.length; i++) {
                if (fillValue != null &&
                        NumberUtil.sub(fillValue.doubleValue(), value[i]) == 0) {
                    continue;
                }
                convertValue[i] = (float) (value[i] * scaleFactory + addOffset);
            }
            return convertValue;
        } else if (o instanceof short[][]) {
            short[][] value = (short[][]) o;
            Float[][] convertValue = new Float[value.length][value[0].length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[0].length; j++) {
                    try {
                        if (fillValue != null
                                && NumberUtil.sub(fillValue.doubleValue(), value[i][j]) == 0) {
                            continue;
                        }
                        convertValue[i][j] = (float) (value[i][j] * scaleFactory + addOffset);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            return convertValue;
        } else if (o instanceof int[][]) {
            int[][] value = (int[][]) o;
            Float[][] convertValue = new Float[value.length][value[0].length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[0].length; j++) {
                    if (fillValue != null
                            && NumberUtil.sub(fillValue.doubleValue(), value[i][j]) == 0) {
                        continue;
                    }
                    convertValue[i][j] = (float) (value[i][j] * scaleFactory + addOffset);
                }
            }
            return convertValue;
        } else if (o instanceof float[][]) {
            float[][] value = (float[][]) o;
            Float[][] convertValue = new Float[value.length][value[0].length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[0].length; j++) {
                    try {
                        if (fillValue != null
                                && NumberUtil.sub(fillValue.doubleValue(), value[i][j]) == 0) {
                            continue;
                        }
                        convertValue[i][j] = (float) (value[i][j] * scaleFactory + addOffset);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            return convertValue;
        } else if (o instanceof short[][][]) {
            short[][][] value = (short[][][]) o;
            Float[][][] convertValue = new Float[value.length][value[0].length]
                    [value[0][0].length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[0].length; j++) {
                    for (int k = 0; k < value[0][0].length; k++) {
                        if (fillValue != null && NumberUtil.sub(fillValue.doubleValue(),
                                value[i][j][k]) == 0) {
                            continue;
                        }
                        convertValue[i][j][k] = (float) (value[i][j][k]
                                * scaleFactory + addOffset);
                    }
                }
            }
            return convertValue;
        } else if (o instanceof float[][][]) {
            float[][][] value = (float[][][]) o;
            Float[][][] convertValue = new Float[value.length][value[0].length]
                    [value[0][0].length];
            for (int i = 0; i < value.length; i++) {
                for (int j = 0; j < value[0].length; j++) {
                    for (int k = 0; k < value[0][0].length; k++) {
                        if (fillValue != null && NumberUtil.sub(fillValue.doubleValue(),
                                value[i][j][k]) == 0) {
                            continue;
                        }
                        convertValue[i][j][k] = (float) (value[i][j][k]
                                * scaleFactory + addOffset);
                    }
                }
            }
            return convertValue;
        }else if (o instanceof short[][][][]) {
            short[][][][] value = (short[][][][]) o;
            Float[][][][] convertValue = new Float[value.length][value[0].length]
                    [value[0][0].length][value[0][0][0].length];
            for (int a = 0; a < value.length; a++) {
                for (int b = 0; b < value[0].length; b++) {
                    for (int c = 0; c < value[0][0].length; c++) {
                        for (int d = 0; d < value[0][0][0].length; d++) {
                            if (fillValue != null
                                    && NumberUtil.sub(fillValue.doubleValue(),
                                    value[a][b][c][d]) == 0) {
                                continue;
                            }
                            convertValue[a][b][c][d] = (float)
                                    (value[a][b][c][d] * scaleFactory + addOffset);
                        }
                    }
                }
            }
            return convertValue;
        } else {
            throw new RuntimeException("暂不支持");
        }
    }

    private static List<Attr> getAttrs(NetcdfFile dataFile) {
        List<Attribute> globalAttributes = dataFile.getGlobalAttributes();
        List<Attr> attrs = Lists.newArrayList();
        for (Attribute globalAttribute : globalAttributes) {
            String shortName = globalAttribute.getShortName();
            String s = globalAttribute.getValues().toString();
            Attr attr = new Attr(shortName, s);
            attrs.add(attr);
        }
        return attrs;
    }

    private static Scale loadScale(Variable variable) {
        Double scaleFactory = null;
        Double addOffset = null;
        Double fillValue = null;
        for (Attribute attribute : variable.getAttributes()) {
            String attrName = attribute.getShortName();
            String val = attribute.getValues().toString();
            switch (attrName) {
                case "scale_factor":
                    scaleFactory = Double.parseDouble(val);
                    break;
                case "Slope":
                    scaleFactory = Double.parseDouble(val);
                    break;
                case "add_offset":
                    addOffset = Double.parseDouble(val);
                    break;
                case "_FillValue":
                    fillValue = Double.parseDouble(val);
                    break;
                case "fill_value":
                    fillValue = Double.parseDouble(val);
                    break;
                case "FillValue":
                    fillValue = Double.parseDouble(val);
                    break;
                default:
                    break;
            }
        }
        return new Scale(scaleFactory, addOffset, fillValue);
    }


    public static Float[][] toFloatArray2(Float[][][] a) {
        Float[][] result = new Float[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                for (int k = 0; k < a[i][j].length; k++) {
                    try {
                        Float v = a[i][j][k];
                        if (v != null) {
                            result[i][j] = v;
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            }
        }
        return result;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Attr {
        private String shortName;
        private String value;
    }

}
