#优酷菜单#
- 定义布局
- 在Activity中找到相关的布局Layout和控件ImageButton
- 给控件上面的ImageButton添加点击监听
- 按钮点击控制
	- 默认三级菜单都显示(Level1表示最里Home层，Level2表示中间Menu层,Level3表示最外层)
	- 点击Menu层，控制Level3层的显示或者隐藏
	- 点击Home层，控制Level2的显示及隐藏，需要注意的是Level2隐藏的时候如果Level3处于显示状态，应该将其也隐藏
- Menu点击的控制
	- 如果有显示，都隐藏，隐藏动画的延时用ra.setStartOffset(startOffset);
	- 如果都没处于隐藏状态，则顺序显示出来。
##需要注意的地方##
- 动画执行完成后停留在最后位置ra.setFillAfter(true);
- 菜单隐藏后，菜单布局中的条目应该是不可用的，重新显示后将其设为可用 
- 某个动画在执行中的时候，不能执行其他动画，这个需求可以通过实现AnimationListener，设置一个计数器runningAnimationCount。当其中的onAnimationStart被调用时runningAnimationCount++，反之，onAnimationEnd被调用时runningAnimationCount--