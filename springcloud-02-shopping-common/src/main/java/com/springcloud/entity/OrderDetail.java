package com.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * ORDER_DETAILS表对应的实体类，用于封装一行订单明细信息
 * @author Ya
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail implements java.io.Serializable{
	/**
	 * 序列化编号
	 */
	private static final long serialVersionUID = -8959059229768491234L;
	/**
	 * 商品细节编号
	 */
    private Integer orderDetailId;
    /**
     * 订单编号
     */
    private Integer orderId;
    /**
     * 商品编号
     */
    private Integer goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 交易价格
     */
    private Double transactionPrice;
    /**
     * 交易数量
     */
    private String transactionCount;
    /**
     * 	用于保存订单信息的商品
     */
    private Goods goods;
    
    /**
     * 根据商品编号重写equals()方法：判断新生成的对象是否相等
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		if (goodsId == null) {
			if (other.goodsId != null)
				return false;
		} else if (!goodsId.equals(other.goodsId))
			return false;
		return true;
	}
	/**
	 * 根据商品编号重写hashCode()方法
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((goodsId == null) ? 0 : goodsId.hashCode());
		return result;
	}
    
    
}