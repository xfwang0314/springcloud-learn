import cn.hutool.http.HttpUtil;

import java.time.ZonedDateTime;

public class T2 {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        String str =  HttpUtil.post("","123");
        boolean flag = HttpUtil.createPost("http://share.estarspace.com:8084/auth/queryPlat").timeout(1000).execute().isOk();
        System.out.println(str);
    }
}
