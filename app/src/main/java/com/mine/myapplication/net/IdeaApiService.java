package com.mine.myapplication.net;

import com.mine.myapplication.entity.ImgListEntity;
import com.mine.myapplication.entity.ImgListPageEntity;

import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;


public interface IdeaApiService {
    /**
     * 网络请求超时时间毫秒
     */
    int DEFAULT_TIMEOUT = 20000;

    String HOST = "http://47.100.23.175:8080/";
    String API_SERVER_URL = HOST;


    @GET("imginfo/imglist")
    Observable<BasicResponse<List<ImgListEntity>>> getImgList();

    @GET("imginfo/imglistpage")
    Observable<MyBasicResponse<ImgListPageEntity.ListImgPageInfoBean>> getImgListPage(@Query("page") int page,@Query("size") int size);

    /**
     * @param page
     * @param number
     * @return
     */
    @Headers("Cache-Control: public, max-age=100")//设置缓存 缓存时间为100s
    @GET("everySay/selectAll.do")
    Observable<BasicResponse<List<ImgListEntity>>> lookBack(@Query("page") int page, @Query("rows") int number);


    @POST("upload/uploadFile.do")
    Observable<BasicResponse> uploadFiles(@Part("filename") String description,
                                          @Part("pic\"; filename=\"image1.png") RequestBody imgs1,
                                          @Part("pic\"; filename=\"image2.png") RequestBody imgs2);

    @POST("upload/uploadFile.do")
    Observable<BasicResponse> uploadFiles(@Part("filename") String description, @PartMap() Map<String, RequestBody> maps);

}
