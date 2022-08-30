package com.mimi;


import java.io.*;

public class grafana {
    public static void main(String[] args) throws IOException {
        String[] as = new String[]{"(SZGQ-B8F)-01(4P)",
                "(SZGQ-B8F)-02(4P)",
                "(SZGQ-B8F)-03(6P)",
                "(SZGQ-B8F)-04(6P)",
                "(SZGQ-B8F)-05(8P)[VC]",
                "(SZGQ-B8F)-06(4P)",
                "(SZGQ-B8F)-07(15P)[VC]",
                "(SZGQ-B8F)-08(4P)",
                "(SZGQ-B8F)-09(6P)",
                "(SZGQ-B8F)-10(10P)[VC]",
                "(SZGQ-B8F)-11(4P)",
                "(SZGQ-B8F)-12(4P)",
                "(SZGQ-B9F)-01(8P)[VC]",
                "(SZGQ-B9F)-02(10P)[VC]",
                "(SZGQ-B9F)-03(4P)",
                "(SZGQ-B9F)-04(15P)[VC]",
                "(SZGQ-B9F)-05(16P)[VC]",
                "(SZGQ-B9F)-06(10P)[VC]",
                "(SZGQ-B9F)-07(4P)",
                "(SZGQ-B9F)-08(4P)",
                "(SZGQ-B10F)-01(4P)",
                "(SZGQ-B10F)-02(4P)",
                "(SZGQ-B10F)-03(8P)[VC]",
                "(SZGQ-B10F)-04(6P)",
                "(SZGQ-B10F)-05(6P)",
                "(SZGQ-B10F)-06(8P)[VC]",
                "(SZGQ-B10F)-07(4P)",
                "(SZGQ-B10F)-08(15P)[VC]",
                "(SZGQ-B10F)-09(4P)",
                "(SZGQ-B10F)-10(6P)",
                "(SZGQ-B10F)-11(10P)[VC]",
                "(SZGQ-B10F)-12(4P)",
                "(SZGQ-B10F)-13(4P)",
                "(SZGQ-B11F)-01(4P)",
                "(SZGQ-B11F)-02(4P)",
                "(SZGQ-B11F)-03(6P)",
                "(SZGQ-B11F)-04(6P)",
                "(SZGQ-B11F)-05(8P)[VC]",
                "(SZGQ-B11F)-06(4P)",
                "(SZGQ-B11F)-07(15P)[VC]",
                "(SZGQ-B11F)-08(4P)",
                "(SZGQ-B11F)-09(6P)",
                "(SZGQ-B11F)-10(10P)[VC]",
                "(SZGQ-B11F)-11(4P)",
                "(SZGQ-B11F)-12(4P)",
                "(SZGQ-B12F)-01(4P)",
                "(SZGQ-B12F)-02(4P)",
                "(SZGQ-B12F)-03(4P)",
                "(SZGQ-B12F)-04(10P)[VC]",
                "(SZGQ-B12F)-05(4P)",
                "(SZGQ-B12F)-06(12P)[VC]",
                "(SZGQ-B12F)-07(4P)",
                "(SZGQ-B12F)-08(4P)",
                "(SZGQ-B12F)-09(6P)[VC]",
                "(SZGQ-B13F)-01(4P)",
                "(SZGQ-B13F)-02(4P)",
                "(SZGQ-B13F)-03(8P)[VC]",
                "(SZGQ-B13F)-04(6P)",
                "(SZGQ-B13F)-05(6P)",
                "(SZGQ-B13F)-06(8P)[VC]",
                "(SZGQ-B13F)-07(4P)",
                "(SZGQ-B13F)-08(15P)[VC]",
                "(SZGQ-B13F)-09(4P)",
                "(SZGQ-B13F)-10(6P)",
                "(SZGQ-B13F)-11(10P)[VC]",
                "(SZGQ-B13F)-12(4P)",
                "(SZGQ-B13F)-13(4P)",
                "(SZGQ-B14F)-01(4P)",
                "(SZGQ-B14F)-02(4P)",
                "(SZGQ-B14F)-03(8P)[VC]",
                "(SZGQ-B14F)-04(6P)",
                "(SZGQ-B14F)-05(6P)",
                "(SZGQ-B14F)-06(8P)[VC]",
                "(SZGQ-B14F)-07(4P)",
                "(SZGQ-B14F)-08(15P)[VC]",
                "(SZGQ-B14F)-09(4P)",
                "(SZGQ-B14F)-10(6P)",
                "(SZGQ-B14F)-11(10P)[VC]",
                "(SZGQ-B14F)-12(4P)",
                "(SZGQ-B14F)-13(4P)",
                "(SZGQ-B15F)-01(4P)",
                "(SZGQ-B15F)-02(4P)",
                "(SZGQ-B15F)-03(4P)",
                "(SZGQ-B15F)-04(10P)[VC]",
                "(SZGQ-B15F)-05(4P)",
                "(SZGQ-B15F)-06(12P)[VC]",
                "(SZGQ-B15F)-07(4P)",
                "(SZGQ-B15F)-08(4P)",
                "(SZGQ-B15F)-09(6P)[VC]",
                "(SZGQ-B16F)-01(4P)",
                "(SZGQ-B16F)-02(4P)",
                "(SZGQ-B16F)-03(6P)",
                "(SZGQ-B16F)-04(6P)",
                "(SZGQ-B16F)-05(8P)[VC]",
                "(SZGQ-B16F)-06(4P)",
                "(SZGQ-B16F)-07(15P)[VC]",
                "(SZGQ-B16F)-08(4P)",
                "(SZGQ-B16F)-09(6P)",
                "(SZGQ-B16F)-10(10P)[VC]",
                "(SZGQ-B16F)-11(4P)",
                "(SZGQ-B16F)-12(4P)"
                };
        String[] bs = new String[]{"B8F-01",
                "B8F-02",
                "B8F-03",
                "B8F-04",
                "B8F-05",
                "B8F-06",
                "B8F-07",
                "B8F-08",
                "B8F-09",
                "B8F-10",
                "B8F-11",
                "B8F-12",
                "B9F-01",
                "B9F-02",
                "B9F-03",
                "B9F-04",
                "B9F-05",
                "B9F-06",
                "B9F-07",
                "B9F-08",
                "B10F-01",
                "B10F-02",
                "B10F-03",
                "B10F-04",
                "B10F-05",
                "B10F-06",
                "B10F-07",
                "B10F-08",
                "B10F-09",
                "B10F-10",
                "B10F-11",
                "B10F-12",
                "B10F-13",
                "B11F-01",
                "B11F-02",
                "B11F-03",
                "B11F-04",
                "B11F-05",
                "B11F-06",
                "B11F-07",
                "B11F-08",
                "B11F-09",
                "B11F-10",
                "B11F-11",
                "B11F-12",
                "B12F-01",
                "B12F-02",
                "B12F-03",
                "B12F-04",
                "B12F-05",
                "B12F-06",
                "B12F-07",
                "B12F-08",
                "B12F-09",
                "B13F-01",
                "B13F-02",
                "B13F-03",
                "B13F-04",
                "B13F-05",
                "B13F-06",
                "B13F-07",
                "B13F-08",
                "B13F-09",
                "B13F-10",
                "B13F-11",
                "B13F-12",
                "B13F-13",
                "B14F-01",
                "B14F-02",
                "B14F-03",
                "B14F-04",
                "B14F-05",
                "B14F-06",
                "B14F-07",
                "B14F-08",
                "B14F-09",
                "B14F-10",
                "B14F-11",
                "B14F-12",
                "B14F-13",
                "B15F-01",
                "B15F-02",
                "B15F-03",
                "B15F-04",
                "B15F-05",
                "B15F-06",
                "B15F-07",
                "B15F-08",
                "B15F-09",
                "B16F-01",
                "B16F-02",
                "B16F-03",
                "B16F-04",
                "B16F-05",
                "B16F-06",
                "B16F-07",
                "B16F-08",
                "B16F-09",
                "B16F-10",
                "B16F-11",
                "B16F-12"
                };

        FileWriter fw = new FileWriter(new File("grafana.json"),true);
        fw.write("{\n" +
                "  \"annotations\": {\n" +
                "    \"list\": [\n" +
                "      {\n" +
                "        \"builtIn\": 1,\n" +
                "        \"datasource\": \"-- Grafana --\",\n" +
                "        \"enable\": true,\n" +
                "        \"hide\": true,\n" +
                "        \"iconColor\": \"rgba(0, 211, 255, 1)\",\n" +
                "        \"name\": \"Annotations & Alerts\",\n" +
                "        \"target\": {\n" +
                "          \"limit\": 100,\n" +
                "          \"matchAny\": false,\n" +
                "          \"tags\": [],\n" +
                "          \"type\": \"dashboard\"\n" +
                "        },\n" +
                "        \"type\": \"dashboard\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"editable\": true,\n" +
                "  \"fiscalYearStartMonth\": 0,\n" +
                "  \"graphTooltip\": 0,\n" +
                "  \"id\": 727,\n" +
                "  \"links\": [],\n" +
                "  \"liveNow\": false,\n" +
                "  \"panels\": [\n");

        int x = 0;
        int y = 0;
        for (int i = 0; i < 101; i++) {
            if(x>21) {
                x = 0;
                y += 3;
            }
            fw.write("{\n" +
                    "      \"description\": \"\",\n" +
                    "      \"fieldConfig\": {\n" +
                    "        \"defaults\": {\n" +
                    "          \"color\": {\n" +
                    "            \"mode\": \"thresholds\"\n" +
                    "          },\n" +
                    "          \"mappings\": [\n" +
                    "            {\n" +
                    "              \"options\": {\n" +
                    "                \"0\": {\n" +
                    "                  \"index\": 1,\n" +
                    "                  \"text\": \"Down\"\n" +
                    "                },\n" +
                    "                \"1\": {\n" +
                    "                  \"index\": 0,\n" +
                    "                  \"text\": \"Up\"\n" +
                    "                }\n" +
                    "              },\n" +
                    "              \"type\": \"value\"\n" +
                    "            }\n" +
                    "          ],\n" +
                    "          \"thresholds\": {\n" +
                    "            \"mode\": \"absolute\",\n" +
                    "            \"steps\": [\n" +
                    "              {\n" +
                    "                \"color\": \"dark-green\",\n" +
                    "                \"value\": null\n" +
                    "              },\n" +
                    "              {\n" +
                    "                \"color\": \"dark-red\",\n" +
                    "                \"value\": 0\n" +
                    "              },\n" +
                    "              {\n" +
                    "                \"color\": \"dark-green\",\n" +
                    "                \"value\": 1\n" +
                    "              }\n" +
                    "            ]\n" +
                    "          }\n" +
                    "        },\n" +
                    "        \"overrides\": []\n" +
                    "      },\n" +
                    "      \"gridPos\": {\n" +
                    "        \"h\": 3,\n" +
                    "        \"w\": 3,\n" +
                    "        \"x\": "+x+",\n" +
                    "        \"y\": "+y+"\n" +
                    "      },\n" +
                    "      \"id\": 41,\n" +
                    "      \"options\": {\n" +
                    "        \"colorMode\": \"background\",\n" +
                    "        \"graphMode\": \"none\",\n" +
                    "        \"justifyMode\": \"auto\",\n" +
                    "        \"orientation\": \"horizontal\",\n" +
                    "        \"reduceOptions\": {\n" +
                    "          \"calcs\": [\n" +
                    "            \"lastNotNull\"\n" +
                    "          ],\n" +
                    "          \"fields\": \"\",\n" +
                    "          \"values\": false\n" +
                    "        },\n" +
                    "        \"textMode\": \"auto\"\n" +
                    "      },\n" +
                    "      \"pluginVersion\": \"8.4.1\",\n" +
                    "      \"targets\": [\n" +
                    "        {\n" +
                    "          \"_nids\": [\n" +
                    "            \"3301\"\n" +
                    "          ],\n" +
                    "          \"category\": 1,\n" +
                    "          \"datasource\": {\n" +
                    "            \"type\": \"n9e-datasource\",\n" +
                    "            \"uid\": \"4JxRdWeMz\"\n" +
                    "          },\n" +
                    "          \"refId\": \"A\",\n" +
                    "          \"selectedEndpointsIdent\": [\n" +
                    "            \"=all\"\n" +
                    "          ],\n" +
                    "          \"selectedMetric\": \"gbooking.ipad.syncStatus\",\n" +
                    "          \"selectedNid\": [\n" +
                    "            3295\n" +
                    "          ],\n" +
                    "          \"selectedTagkv\": [\n" +
                    "            {\n" +
                    "              \"tagk\": \"room\",\n" +
                    "              \"tagv\": [\n" +
                    "                \""+as[i]+"\"\n" +
                    "              ]\n" +
                    "            }\n" +
                    "          ],\n" +
                    "          \"tagkv\": [\n" +
                    "            {\n" +
                    "              \"tagk\": \"room\",\n" +
                    "              \"tagv\": [\n" +
                    "                \"(SZGQ-3F)-Barcelona-巴塞罗那(10P)\",\n" +
                    "                \"(SZGQ-3F)-Berlin-柏林(4P)\",\n" +
                    "                \"(SZGQ-3F)-Boston-波士顿(6P)\",\n" +
                    "                \"(SZGQ-3F)-Dubai-迪拜(20P)[VC]\",\n" +
                    "                \"(SZGQ-3F)-London-伦敦(4P)\",\n" +
                    "                \"(SZGQ-3F)-Los-Angeles-洛杉矶(4P)\",\n" +
                    "                \"(SZGQ-3F)-Luxembourg-卢森堡(8P)\",\n" +
                    "                \"(SZGQ-3F)-Madrid-马德里(4P)\",\n" +
                    "                \"(SZGQ-3F)-Melbourne-墨尔本(4P)\",\n" +
                    "                \"(SZGQ-3F)-Milan-米兰(4P)\",\n" +
                    "                \"(SZGQ-3F)-Moscow-莫斯科(12P)\",\n" +
                    "                \"(SZGQ-3F)-New-York-纽约(10P)[VC]\",\n" +
                    "                \"(SZGQ-3F)-Osaka-大阪(4P)\",\n" +
                    "                \"(SZGQ-3F)-Paris-巴黎(12P)[VC]\",\n" +
                    "                \"(SZGQ-3F)-Sao-Paulo-圣保罗(4P)\",\n" +
                    "                \"(SZGQ-3F)-Seattle-西雅图(6P)\",\n" +
                    "                \"(SZGQ-3F)-Sydney-悉尼(6P)\",\n" +
                    "                \"(SZGQ-3F)-Toronto-多伦多(4P)\",\n" +
                    "                \"(SZGQ-3F)-Training-Room-培训室\",\n" +
                    "                \"(SZGQ-3F)-Vancouver-温哥华(20P)[VC]\",\n" +
                    "                \"(SZGQ-3F)-Vienna-维也纳(4P)\",\n" +
                    "                \"(SZGQ-3F)-Washington-华盛顿(12P)[VC]\",\n" +
                    "                \"(SZGQ-3F)-Zurich-苏黎世(8P)\",\n" +
                    "                \"(SZGQ-A4F)01(20P)[VC]\",\n" +
                    "                \"(SZGQ-A4F)02(10P)[VC]\",\n" +
                    "                \"(SZGQ-A4F)03(8P)\",\n" +
                    "                \"(SZGQ-A4F)04(20P)[VC]\",\n" +
                    "                \"(SZGQ-A4F)05(8P)\",\n" +
                    "                \"(SZGQ-A4F)06(8P)\",\n" +
                    "                \"(SZGQ-A4F)07(4P)\",\n" +
                    "                \"(SZGQ-A4F)08(6P)\",\n" +
                    "                \"(SZGQ-A4F)09(8P)[VC]\",\n" +
                    "                \"(SZGQ-A4F)10(4P)\",\n" +
                    "                \"(SZGQ-A4F)11(6P)\",\n" +
                    "                \"(SZGQ-A4F)12(4P)\",\n" +
                    "                \"(SZGQ-A4F)13(6P)\",\n" +
                    "                \"(SZGQ-A4F)14(6P)\",\n" +
                    "                \"(SZGQ-A4F)15(4P)\",\n" +
                    "                \"(SZGQ-A4F)16(4P)\",\n" +
                    "                \"(SZGQ-A4F)17(4P)\",\n" +
                    "                \"(SZGQ-A5F)01-(20P)[VC]\",\n" +
                    "                \"(SZGQ-A5F)02-(8P)\",\n" +
                    "                \"(SZGQ-A5F)03-(4P)\",\n" +
                    "                \"(SZGQ-A5F)04-(6P)\",\n" +
                    "                \"(SZGQ-A5F)05-(10P)[VC]\",\n" +
                    "                \"(SZGQ-A5F)06-(4P)\",\n" +
                    "                \"(SZGQ-A5F)07-(6P)\",\n" +
                    "                \"(SZGQ-A5F)08-(4P)\",\n" +
                    "                \"(SZGQ-A5F)09-(6P)\",\n" +
                    "                \"(SZGQ-A5F)10-(6P)\",\n" +
                    "                \"(SZGQ-A5F)11-(4P)\",\n" +
                    "                \"(SZGQ-A5F)12-(4P)\",\n" +
                    "                \"(SZGQ-A5F)13-(4P)\",\n" +
                    "                \"(SZGQ-A5F)14-(4P)\",\n" +
                    "                \"(SZGQ-A5F)15-(4P)\",\n" +
                    "                \"(SZGQ-A5F)16-(4P)\",\n" +
                    "                \"(SZGQ-A5F)17-(6P)[VC]\",\n" +
                    "                \"(SZGQ-A5F)18-(4P)\",\n" +
                    "                \"(SZGQ-A6F)01-(20P)[VC]\",\n" +
                    "                \"(SZGQ-A6F)02-(10P)[VC]\",\n" +
                    "                \"(SZGQ-A6F)03-(8P)\",\n" +
                    "                \"(SZGQ-A6F)04-(8P)\",\n" +
                    "                \"(SZGQ-A6F)05-(20P)[VC]\",\n" +
                    "                \"(SZGQ-A6F)06-(12P)[VC]\",\n" +
                    "                \"(SZGQ-A6F)07-(8P)\",\n" +
                    "                \"(SZGQ-A6F)08-(4P)\",\n" +
                    "                \"(SZGQ-A6F)09-(6P)\",\n" +
                    "                \"(SZGQ-A6F)10-(8P)[VC]\",\n" +
                    "                \"(SZGQ-A6F)11-(4P)\",\n" +
                    "                \"(SZGQ-A6F)12-(6P)\",\n" +
                    "                \"(SZGQ-A6F)13-(4P)\",\n" +
                    "                \"(SZGQ-A6F)14-(6P)\",\n" +
                    "                \"(SZGQ-A6F)15-(6P)\",\n" +
                    "                \"(SZGQ-A6F)16-(4P)\",\n" +
                    "                \"(SZGQ-A6F)17-(4P)\",\n" +
                    "                \"(SZGQ-A6F)18-(4P)\",\n" +
                    "                \"(SZGQ-B10F)-01(4P)\",\n" +
                    "                \"(SZGQ-B10F)-02(4P)\",\n" +
                    "                \"(SZGQ-B10F)-03(8P)[VC]\",\n" +
                    "                \"(SZGQ-B10F)-04(6P)\",\n" +
                    "                \"(SZGQ-B10F)-05(6P)\",\n" +
                    "                \"(SZGQ-B10F)-06(8P)[VC]\",\n" +
                    "                \"(SZGQ-B10F)-07(4P)\",\n" +
                    "                \"(SZGQ-B10F)-08(15P)[VC]\",\n" +
                    "                \"(SZGQ-B10F)-09(4P)\",\n" +
                    "                \"(SZGQ-B10F)-10(6P)\",\n" +
                    "                \"(SZGQ-B10F)-11(10P)[VC]\",\n" +
                    "                \"(SZGQ-B10F)-12(4P)\",\n" +
                    "                \"(SZGQ-B10F)-13(4P)\",\n" +
                    "                \"(SZGQ-B11F)-01(4P)\",\n" +
                    "                \"(SZGQ-B11F)-02(4P)\",\n" +
                    "                \"(SZGQ-B11F)-03(6P)\",\n" +
                    "                \"(SZGQ-B11F)-04(6P)\",\n" +
                    "                \"(SZGQ-B11F)-05(8P)[VC]\",\n" +
                    "                \"(SZGQ-B11F)-06(4P)\",\n" +
                    "                \"(SZGQ-B11F)-07(15P)[VC]\",\n" +
                    "                \"(SZGQ-B11F)-08(4P)\",\n" +
                    "                \"(SZGQ-B11F)-09(6P)\",\n" +
                    "                \"(SZGQ-B11F)-10(10P)[VC]\",\n" +
                    "                \"(SZGQ-B11F)-11(4P)\",\n" +
                    "                \"(SZGQ-B11F)-12(4P)\",\n" +
                    "                \"(SZGQ-B12F)-01(4P)\",\n" +
                    "                \"(SZGQ-B12F)-02(4P)\",\n" +
                    "                \"(SZGQ-B12F)-03(4P)\",\n" +
                    "                \"(SZGQ-B12F)-04(10P)[VC]\",\n" +
                    "                \"(SZGQ-B12F)-05(4P)\",\n" +
                    "                \"(SZGQ-B12F)-06(12P)[VC]\",\n" +
                    "                \"(SZGQ-B12F)-07(4P)\",\n" +
                    "                \"(SZGQ-B12F)-08(4P)\",\n" +
                    "                \"(SZGQ-B12F)-09(6P)[VC]\",\n" +
                    "                \"(SZGQ-B13F)-01(4P)\",\n" +
                    "                \"(SZGQ-B13F)-02(4P)\",\n" +
                    "                \"(SZGQ-B13F)-03(8P)[VC]\",\n" +
                    "                \"(SZGQ-B13F)-04(6P)\",\n" +
                    "                \"(SZGQ-B13F)-05(6P)\",\n" +
                    "                \"(SZGQ-B13F)-06(8P)[VC]\",\n" +
                    "                \"(SZGQ-B13F)-07(4P)\",\n" +
                    "                \"(SZGQ-B13F)-08(15P)[VC]\",\n" +
                    "                \"(SZGQ-B13F)-09(4P)\",\n" +
                    "                \"(SZGQ-B13F)-10(6P)\",\n" +
                    "                \"(SZGQ-B13F)-11(10P)[VC]\",\n" +
                    "                \"(SZGQ-B13F)-12(4P)\",\n" +
                    "                \"(SZGQ-B13F)-13(4P)\",\n" +
                    "                \"(SZGQ-B14F)-01(4P)\",\n" +
                    "                \"(SZGQ-B14F)-02(4P)\",\n" +
                    "                \"(SZGQ-B14F)-03(8P)[VC]\",\n" +
                    "                \"(SZGQ-B14F)-04(6P)\",\n" +
                    "                \"(SZGQ-B14F)-05(6P)\",\n" +
                    "                \"(SZGQ-B14F)-06(8P)[VC]\",\n" +
                    "                \"(SZGQ-B14F)-07(4P)\",\n" +
                    "                \"(SZGQ-B14F)-08(15P)[VC]\",\n" +
                    "                \"(SZGQ-B14F)-09(4P)\",\n" +
                    "                \"(SZGQ-B14F)-10(6P)\",\n" +
                    "                \"(SZGQ-B14F)-11(10P)[VC]\",\n" +
                    "                \"(SZGQ-B14F)-12(4P)\",\n" +
                    "                \"(SZGQ-B14F)-13(4P)\",\n" +
                    "                \"(SZGQ-B15F)-01(4P)\",\n" +
                    "                \"(SZGQ-B15F)-02(4P)\",\n" +
                    "                \"(SZGQ-B15F)-03(4P)\",\n" +
                    "                \"(SZGQ-B15F)-04(10P)[VC]\",\n" +
                    "                \"(SZGQ-B15F)-05(4P)\",\n" +
                    "                \"(SZGQ-B15F)-06(12P)[VC]\",\n" +
                    "                \"(SZGQ-B15F)-07(4P)\",\n" +
                    "                \"(SZGQ-B15F)-08(4P)\",\n" +
                    "                \"(SZGQ-B15F)-09(6P)[VC]\",\n" +
                    "                \"(SZGQ-B16F)-01(4P)\",\n" +
                    "                \"(SZGQ-B16F)-02(4P)\",\n" +
                    "                \"(SZGQ-B16F)-03(6P)\",\n" +
                    "                \"(SZGQ-B16F)-04(6P)\",\n" +
                    "                \"(SZGQ-B16F)-05(8P)[VC]\",\n" +
                    "                \"(SZGQ-B16F)-06(4P)\",\n" +
                    "                \"(SZGQ-B16F)-07(15P)[VC]\",\n" +
                    "                \"(SZGQ-B16F)-08(4P)\",\n" +
                    "                \"(SZGQ-B16F)-09(6P)\",\n" +
                    "                \"(SZGQ-B16F)-10(10P)[VC]\",\n" +
                    "                \"(SZGQ-B16F)-11(4P)\",\n" +
                    "                \"(SZGQ-B16F)-12(4P)\",\n" +
                    "                \"(SZGQ-B4F)01(8P)[VC]\",\n" +
                    "                \"(SZGQ-B4F)02(4P)\",\n" +
                    "                \"(SZGQ-B4F)03(10P)[VC]\",\n" +
                    "                \"(SZGQ-B4F)04(4P)\",\n" +
                    "                \"(SZGQ-B4F)05(4P)\",\n" +
                    "                \"(SZGQ-B4F)06(4P)\",\n" +
                    "                \"(SZGQ-B4F)07(6P)[VC]\",\n" +
                    "                \"(SZGQ-B4F)08(6P)\",\n" +
                    "                \"(SZGQ-B4F)09(4P)\",\n" +
                    "                \"(SZGQ-B4F)10(4P)\",\n" +
                    "                \"(SZGQ-B4F)11(4P)\",\n" +
                    "                \"(SZGQ-B5F)01-(12P)\",\n" +
                    "                \"(SZGQ-B5F)02-(12P)[VC]\",\n" +
                    "                \"(SZGQ-B5F)03-(12P)[VC]\",\n" +
                    "                \"(SZGQ-B5F)04-(10P)[VC]\",\n" +
                    "                \"(SZGQ-B5F)05-(4P)\",\n" +
                    "                \"(SZGQ-B5F)06-(4P)\",\n" +
                    "                \"(SZGQ-B5F)07-(4P)\",\n" +
                    "                \"(SZGQ-B5F)08-(6P)\",\n" +
                    "                \"(SZGQ-B5F)09-(6P)[VC]\",\n" +
                    "                \"(SZGQ-B5F)10-(4P)\",\n" +
                    "                \"(SZGQ-B5F)11-(4P)\",\n" +
                    "                \"(SZGQ-B5F)12-(4P)\",\n" +
                    "                \"(SZGQ-B5F)13-(8P)[VC]\",\n" +
                    "                \"(SZGQ-B5F)14-(4P)\",\n" +
                    "                \"(SZGQ-B6F)01-(10P)[VC]\",\n" +
                    "                \"(SZGQ-B6F)02-(4P)\",\n" +
                    "                \"(SZGQ-B6F)03-(4P)\",\n" +
                    "                \"(SZGQ-B6F)04-(12P)[VC]\",\n" +
                    "                \"(SZGQ-B6F)05-(4P)\",\n" +
                    "                \"(SZGQ-B6F)06-(4P)\",\n" +
                    "                \"(SZGQ-B6F)07-(4P)\",\n" +
                    "                \"(SZGQ-B6F)08-(12P)[VC]\",\n" +
                    "                \"(SZGQ-B6F)09-(4P)\",\n" +
                    "                \"(SZGQ-B6F)10-(6P)\",\n" +
                    "                \"(SZGQ-B6F)11-(6P)\",\n" +
                    "                \"(SZGQ-B6F)12-(4P)\",\n" +
                    "                \"(SZGQ-B6F)14-(4P)\",\n" +
                    "                \"(SZGQ-B6F)15-(8P)[VC]\",\n" +
                    "                \"(SZGQ-B6F)16-(4P)\",\n" +
                    "                \"(SZGQ-B8F)-01(4P)\",\n" +
                    "                \"(SZGQ-B8F)-02(4P)\",\n" +
                    "                \"(SZGQ-B8F)-03(6P)\",\n" +
                    "                \"(SZGQ-B8F)-04(6P)\",\n" +
                    "                \"(SZGQ-B8F)-05(8P)[VC]\",\n" +
                    "                \"(SZGQ-B8F)-06(4P)\",\n" +
                    "                \"(SZGQ-B8F)-07(15P)[VC]\",\n" +
                    "                \"(SZGQ-B8F)-08(4P)\",\n" +
                    "                \"(SZGQ-B8F)-09(6P)\",\n" +
                    "                \"(SZGQ-B8F)-10(10P)[VC]\",\n" +
                    "                \"(SZGQ-B8F)-11(4P)\",\n" +
                    "                \"(SZGQ-B8F)-12(4P)\",\n" +
                    "                \"(SZGQ-B9F)-01(8P)[VC]\",\n" +
                    "                \"(SZGQ-B9F)-02(10P)[VC]\",\n" +
                    "                \"(SZGQ-B9F)-03(4P)\",\n" +
                    "                \"(SZGQ-B9F)-04(15P)[VC]\",\n" +
                    "                \"(SZGQ-B9F)-05(16P)[VC]\",\n" +
                    "                \"(SZGQ-B9F)-06(10P)[VC]\",\n" +
                    "                \"(SZGQ-B9F)-07(4P)\",\n" +
                    "                \"(SZGQ-B9F)-08(4P)\",\n" +
                    "                \"(SZGQ-C4F)01(6P)\",\n" +
                    "                \"(SZGQ-C4F)02(6P)[VC]\",\n" +
                    "                \"(SZGQ-C4F)03(4P)\",\n" +
                    "                \"(SZGQ-C4F)04(8P)[VC]\",\n" +
                    "                \"(SZGQ-C4F)05(12P)[VC]\",\n" +
                    "                \"(SZGQ-C4F)06(12P)[VC]\",\n" +
                    "                \"(SZGQ-C4F)07(12P)[VC]\",\n" +
                    "                \"(SZGQ-C5F)01-(20P)[VC]\"\n" +
                    "              ]\n" +
                    "            }\n" +
                    "          ]\n" +
                    "        }\n" +
                    "      ],\n" +
                    "      \"title\": \""+bs[i]+"\",\n" +
                    "      \"type\": \"stat\"\n" +
                    "    },");
            x += 3;
        }
        fw.write("\n],\n" +
                "  \"refresh\": \"\",\n" +
                "  \"schemaVersion\": 35,\n" +
                "  \"style\": \"dark\",\n" +
                "  \"tags\": [],\n" +
                "  \"templating\": {\n" +
                "    \"list\": []\n" +
                "  },\n" +
                "  \"time\": {\n" +
                "    \"from\": \"now-5m\",\n" +
                "    \"to\": \"now\"\n" +
                "  },\n" +
                "  \"timepicker\": {},\n" +
                "  \"timezone\": \"\",\n" +
                "  \"title\": \"SZGQ - VC Monitoring - 8F-16F\",\n" +
                "  \"uid\": \"uAeNX2W4k\",\n" +
                "  \"version\": 4,\n" +
                "  \"weekStart\": \"\"\n" +
                "}");
        fw.close();
    }
}
