package lh.cn.edu.henu.upto.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=你好，世界。我想加入金山词霸")
    Call<Translation> getCall();
    //@GET("ajax/{a}/{f}/{t}/{w}")
    Call<Translation> getCall(@Path("a") String a, @Path("f") String f, @Path("t") String t, @Path("w") String w);

}
