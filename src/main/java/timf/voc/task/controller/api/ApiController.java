// package timf.voc.task.controller.api;
//
// import timf.voc.task.dto.api.ResponseDTO;
// import timf.voc.task.dto.api.ResultObject;
//
// public abstract class ApiController {
//
// 	protected <T> ResponseDTO<T> ok() {
// 		return ok(null, ResultObject.getSuccess());
// 	}
//
// 	protected <T> ResponseDTO<T> ok(T data) {
// 		return ok(data, ResultObject.getSuccess());
// 	}
//
// 	protected <T> ResponseDTO<T> ok(T data, ResultObject result) {
// 		ResponseDTO<T> obj = new ResponseDTO<>();
// 		obj.setResult(result);
// 		obj.setData(data);
//
// 		return obj;
// 	}
// }