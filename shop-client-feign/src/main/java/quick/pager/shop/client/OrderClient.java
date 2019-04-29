package quick.pager.shop.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quick.pager.shop.constants.Constants;
import quick.pager.shop.dto.OrderDTO;
import quick.pager.shop.dto.SellerOrderDTO;
import quick.pager.shop.fallback.OrderClientFallbackFactory;
import quick.pager.shop.model.UserOrder;
import quick.pager.shop.response.Response;

/**
 * 订单模块
 *
 * @author siguiyang
 */
@FeignClient(value = "shop-order", path = Constants.Module.ORDER, fallbackFactory = OrderClientFallbackFactory.class)
public interface OrderClient {

    /**
     * 订单列表
     */
    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
    Response orders(@RequestBody OrderDTO request);

    /**
     * 订单详情
     */
    @RequestMapping(value = "/detail/user/{orderId}", method = RequestMethod.POST)
    Response orderInfo(@PathVariable("orderId") Long orderId);

    /**
     * 商户订单列表
     */
    @RequestMapping(value = "/sellerOrders", method = RequestMethod.POST)
    Response sellerOrders(@RequestBody SellerOrderDTO request);

    /**
     * 商户订单详情
     */
    @RequestMapping(value = "/sellerOrderInfo/{sellerOrderId}", method = RequestMethod.POST)
    Response sellerOrderInfo(@PathVariable("sellerOrderId") Long sellerOrderId);

    /**
     * 创建订单
     *
     * @param userOrder 订单
     */
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    Response orderCreate(@RequestBody UserOrder userOrder);
}