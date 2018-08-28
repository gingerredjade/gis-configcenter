package com.gis.config.client.controller.test;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Product Controller.
 *
 * 	@ApiOperation 定义REST API的摘要信息
 * 	@ApiImplicitParams 定义REST API的请求参数，需要与@ApiImplicitParam注解组合使用
 * 	@ApiImplicitParam 定义参数具体信息
 * 	@ApiResponses 定义REST API的响应结果，需要与@ApiResponse注解组合使用
 * 	@ApiResponse 定义REST API的状态码与状态消息，可使用自定义状态码
 * 
 * @since 1.0.0 2018年08月18日
 * @author <a href="https://gisnci.com">Hongyu Jiang</a>
 */
@Api(value = "ProductController",description = "产品测试相关的API")
@RestController
@RequestMapping(produces = "application/json")
public class ProductController {

	@ApiOperation("根据产品ID输出该产品ID")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "产品ID", paramType = "path",
					dataType = "long", name = "id", required = true)
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "成功"),
			@ApiResponse(code = 400, message = "错误请求"),
			@ApiResponse(code = 600, message = "无效的产品ID")
	})
	@GetMapping("product/{id}")
	public ResponseEntity returnProductIdById(@PathVariable("id") long id) {
		// TODO: 尚未完成
		return ResponseEntity.badRequest().build();
	}
}
