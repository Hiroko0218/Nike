package cn.tedu.boot01.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    /** 響應狀態碼 (業務狀態碼) */
    private Integer code;
    /** 狀態碼的含意 (比如:用戶名被占用...) */
    private String msg;
    /** 服務端返回给客戶端的具體數據(可能是VO對象,也可能是List集合...) */
    private Object data;

    /** 構造方法 1:適用於不需要返回具體數據的Controller方法 */
    public JsonResult(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    /** 構造方法 2:適用於不需要返回具體數據的Controller方法,使用自定義枚舉類StatusCode */
    public JsonResult(StatusCode statusCode){
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
    }

    /** 構造方法 3:適用於需要返回具體數據的Controller方法,使用自定義枚舉類StatusCode */
    public JsonResult(StatusCode statusCode, Object data){
        this.code = statusCode.getCode();
        this.msg = statusCode.getMsg();
        this.data = data;
    }

    /** 構造方法 4:用於快速構建JsonResult對象,初始化3個属性:code msg data */
    public JsonResult(Object data){
        this.code = StatusCode.SUCCESS.getCode();
        this.msg = StatusCode.SUCCESS.getMsg();
        this.data = data;
    }

    /** 2個靜態方法,用於快速創建JsonResult對象
     * 一種是有返回數據data的;
     * 一種是無反回數據data的;
     */
    public static JsonResult ok(Object data){
        return new JsonResult(data);
    }

    public static JsonResult ok(){
        return ok(null);
    }
}
