package cn.tools.convert;

/**
 * 图片处理类
 * @author hubaoting
 * @date 2015年06月19日 下午05:01:23
 */
public class CutImageVO{
	
	private Integer id;			//序列ID
	private Integer width;		//宽[一般作为最后剪裁或缩放标准]
	private Integer height;		//高[一般作为最后剪裁或缩放标准]
	private Integer rwidth;		//宽2[一般作为最后剪裁或缩放标准]
	private Integer rheight;	//高2[一般作为最后剪裁或缩放标准]
	private Integer x;			//坐标X
	private Integer y;			//坐标Y
	private Integer oldWidth;	//原宽[当前TFSKey的原始宽]
	private Integer oldHeight;	//原高[当前TFSKey的原始高]
	private Integer cutType;	//自动剪裁方式[c1:表示按w,h,rw,rh为依据剪裁，c2:表示按w,h为依据剪裁, c3:表示按w,h,x,y先剪裁，再按rw,rh缩放]
	private boolean apiFlag;	//抽图剪裁方式[true普通方式, flase以cutType传值方式为主]
	private boolean cutFlag;	//是否剪裁[true剪裁, flase不剪裁]
	private Integer userId;		//操作人ID
	private Integer imageId;	//图片所在表中对应数据的序列ID
	private Integer tableType;	//表类型[1新闻表,2图片表,3视频表]
	private Integer _width;		//用来显示下拉列表选定的规则宽
	private Integer _height;	//用来显示下拉列表选定的规则高
	private String _label;		//用来显示下拉列表中的展示信息
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	/**
	 * @return the rwidth
	 */
	public Integer getRwidth() {
		return rwidth;
	}
	/**
	 * @param rwidth the rwidth to set
	 */
	public void setRwidth(Integer rwidth) {
		this.rwidth = rwidth;
	}
	/**
	 * @return the rheight
	 */
	public Integer getRheight() {
		return rheight;
	}
	/**
	 * @param rheight the rheight to set
	 */
	public void setRheight(Integer rheight) {
		this.rheight = rheight;
	}
	/**
	 * @return the x
	 */
	public Integer getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(Integer x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public Integer getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(Integer y) {
		this.y = y;
	}
	/**
	 * @return the cutType
	 */
	public Integer getCutType() {
		return cutType;
	}
	/**
	 * @param cutType the cutType to set
	 */
	public void setCutType(Integer cutType) {
		this.cutType = cutType;
	}
	/**
	 * @return the apiFlag
	 */
	public boolean isApiFlag() {
		return apiFlag;
	}
	/**
	 * @param apiFlag the apiFlag to set
	 */
	public void setApiFlag(boolean apiFlag) {
		this.apiFlag = apiFlag;
	}
	/**
	 * @return the cutFlag
	 */
	public boolean isCutFlag() {
		return cutFlag;
	}
	/**
	 * @param cutFlag the cutFlag to set
	 */
	public void setCutFlag(boolean cutFlag) {
		this.cutFlag = cutFlag;
	}
	/**
	 * @return the _width
	 */
	public Integer get_width() {
		return _width;
	}
	/**
	 * @param _width the _width to set
	 */
	public void set_width(Integer _width) {
		this._width = _width;
	}
	/**
	 * @return the _height
	 */
	public Integer get_height() {
		return _height;
	}
	/**
	 * @param _height the _height to set
	 */
	public void set_height(Integer _height) {
		this._height = _height;
	}
	/**
	 * @return the _label
	 */
	public String get_label() {
		return _label;
	}
	/**
	 * @param _label the _label to set
	 */
	public void set_label(String _label) {
		this._label = _label;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the imageId
	 */
	public String getImageId() {
		return imageId.toString();
	}
	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}
	/**
	 * @return the tableType
	 */
	public Integer getTableType() {
		return tableType;
	}
	/**
	 * @param tableType the tableType to set
	 */
	public void setTableType(Integer tableType) {
		this.tableType = tableType;
	}
	/**
	 * @return the oldWidth
	 */
	public Integer getOldWidth() {
		return oldWidth;
	}
	/**
	 * @param oldWidth the oldWidth to set
	 */
	public void setOldWidth(Integer oldWidth) {
		this.oldWidth = oldWidth;
	}
	/**
	 * @return the oldHeight
	 */
	public Integer getOldHeight() {
		return oldHeight;
	}
	/**
	 * @param oldHeight the oldHeight to set
	 */
	public void setOldHeight(Integer oldHeight) {
		this.oldHeight = oldHeight;
	}


	public static enum Method {
		UPDATE
	}

	/**  
	* @Title: validate  
	* @Description: 根据调用方法验证Bean的正确性  
	* @param @param method
	* @param @return    设定文件  
	* @return boolean    返回类型  
	* @throws  
	*/
	public boolean validate(Method method) {
		switch (method) {
		case UPDATE: {
			if (imageId<=0) {
				return false;
			}
			if (width<=0) {
				return false;
			}
			if (height<=0) {
				return false;
			}
			if (rwidth<=0) {
				return false;
			}
			if (rheight<=0) {
				return false;
			}
			if (tableType<=0) {
				return false;
			}
			if (x<0) {
				return false;
			}
			if (y<0) {
				return false;
			}
			return true;
		}
		default:
			return false;
		}
	}
}
