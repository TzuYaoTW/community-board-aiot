package com.tzuyao.communityboardaiot.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Token {
    public String verify() {

        Random r = new Random();
        ArrayList<String> arrayList = new ArrayList<String>();
        String token = "";
        for(var i = 0; i < 10; i++) {
            int upCase = r.nextInt(26) + 65; // 65 - 90
            String up = String.valueOf((char) upCase); // A-Z
            int downCase = r.nextInt(26) + 97; // 97 - 122
            String down = String.valueOf((char) downCase);
            arrayList.add(up);
            arrayList.add(down);
        }
        Collections.shuffle(arrayList);
        for(var str : arrayList) {
            token += str;
        }
        return token;
    }
}
