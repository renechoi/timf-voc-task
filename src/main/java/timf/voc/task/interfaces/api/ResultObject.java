package timf.voc.task.interfaces.api;// package timf.voc.task.dto.api;
//
// import java.io.Serializable;
//
// import lombok.AllArgsConstructor;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;
// import timf.voc.task.config.api.ResultType;
//
// @Getter
// @Setter
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
// public class ResultObject implements Serializable {
//
// 	public String code;
//
// 	public String desc;
//
// 	public ResultObject(ResultType resultType) {
// 		this.code = resultType.getCode();
// 		this.desc = resultType.getDesc();
// 	}
//
// 	public ResultObject(ResultType resultCode, String message) {
// 		this.code = resultCode.getCode();
// 		this.desc = message;
// 	}
//
// 	public ResultObject(Exception e) {
// 		this.code = ResultType.SYSTEM_ERROR.getCode();
// 		this.desc = ResultType.SYSTEM_ERROR.getDesc();
// 	}
//
// 	public static ResultObject getSuccess() {
// 		return new ResultObject(ResultType.SUCCESS);
// 	}
// }
