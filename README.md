##ShoppingCart##
很简单的例子处理单选全选，结算删除模式。加上Adapter才200行的代码，自认为还算清晰。希望能有作用。

上图:

<img src="screenshot/screenshot1.gif" width=270 height=480>
<img src="screenshot/screenshot2.gif" width=270 height=480>

----
####point
 - 将checked属性维护到实体类中，简化操作
 - boolean mode 处理  编辑/删除
 - BigDecimal 保留一位小数（float相加可能会出现一大堆小数）
 - 自己瞅吧