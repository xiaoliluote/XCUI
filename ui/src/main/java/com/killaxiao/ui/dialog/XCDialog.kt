package com.killaxiao.ui.dialog
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

class XCGeneralDialog{
    var titleText by mutableStateOf("")
    var titleTextSize by mutableStateOf(20.sp)
    var titleTextColor by mutableStateOf(Color(0xff000000))
    var titleTextBlod by mutableStateOf(FontWeight.Bold)
    var topPadding by mutableStateOf(10.dp)
    var startEndPadding by mutableStateOf(10.dp)
    var contentTopPadding by mutableStateOf(10.dp)
    var contentBottomPadding by mutableStateOf(10.dp)
    var contentText by mutableStateOf("")
    var contentTextSize by mutableStateOf(16.sp)
    var contentTextColor by mutableStateOf(Color(0xff000000))
    var contentTextBlod by mutableStateOf(FontWeight.Normal)
    var contentTextAlignment by mutableStateOf(Alignment.CenterHorizontally)
    var lineColor by mutableStateOf(Color(0xff000000))
    var leftButtonText by mutableStateOf("Cancel")
    var leftButtonTextSize by mutableStateOf(16.sp)
    var leftButtonTextColor by mutableStateOf(Color(0xff666666))
    var leftButtonTextBlod by mutableStateOf(FontWeight.Bold)
    var rightButtonText by mutableStateOf("OK")
    var rightButtonTextSize by mutableStateOf(16.sp)
    var rightButtonTextColor by mutableStateOf(Color(0xff000000))
    var rightButtonTextBlod by mutableStateOf(FontWeight.Bold)
    var enterAnim by mutableStateOf(EnterTransition.None)
    var exitAnim by mutableStateOf(ExitTransition.None)
    var cornerShape by mutableStateOf(16.dp)
    var background by mutableStateOf(Color(0xffffffff))
    var btnHeight by mutableStateOf(40.dp)
}

@Composable
fun rememberXCGeneralDialog(param: XCGeneralDialog?=null): XCGeneralDialog {
    return remember {
        param?:XCGeneralDialog()
    }
}

/**
 * 通用的文字弹窗，包含标题、内容、左边（取消）按钮、右边（确认）按钮
 * 标题、内容、左边按钮均可隐藏，分别设置他们的text为空字符串即可
 */
@Composable
fun GeneralTextDialog(showDialog: MutableState<Boolean>, param: XCGeneralDialog = rememberXCGeneralDialog(), leftBtnClick: (() -> Unit)? =null, rightBtnClick: (() -> Unit)? =null){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            visible = showDialog.value,
            enter = param.enterAnim,
            exit = param.exitAnim
        ) {
            Dialog(onDismissRequest = { showDialog.value = false }) {
                Card(
                    modifier = Modifier
                        .padding(start = param.startEndPadding, end = param.startEndPadding)
                ) {
                    Column(Modifier.background(color = param.background, shape = RoundedCornerShape(param.cornerShape))) {
                        Spacer(Modifier.height(param.topPadding))
                        if(param.titleText.isNotBlank()){
                            Text(text = param.titleText, color = param.titleTextColor, fontSize = param.titleTextSize, fontWeight = param.titleTextBlod,
                                modifier = Modifier.align(Alignment.CenterHorizontally))
                            Spacer(Modifier.height(param.contentTopPadding))
                        }
                        if(param.contentText.isNotBlank()){
                            Text(text = param.contentText, color = param.contentTextColor, fontSize = param.contentTextSize, fontWeight = param.contentTextBlod,
                                modifier = Modifier.align(param.contentTextAlignment))
                            Spacer(Modifier.height(param.contentBottomPadding))
                        }
                        HorizontalDivider(modifier = Modifier.height(1.dp).fillMaxWidth(), color = param.lineColor)
                        Row(Modifier.height(param.btnHeight)) {
                            if(param.leftButtonText.isNotBlank()){
                                Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable{
                                    leftBtnClick?.invoke()
                                }, contentAlignment = Alignment.Center) {
                                    Text(text = param.leftButtonText, color = param.leftButtonTextColor, fontSize = param.leftButtonTextSize,
                                        fontWeight = param.leftButtonTextBlod)
                                }
                                VerticalDivider(modifier = Modifier.width(1.dp), color = param.lineColor)
                            }
                            Box(modifier = Modifier.weight(1f).fillMaxHeight().clickable{
                                rightBtnClick?.invoke()
                            }, contentAlignment = Alignment.Center){
                                Text(text = param.rightButtonText, color = param.rightButtonTextColor, fontSize = param.rightButtonTextSize,
                                    lineHeight = param.rightButtonTextSize,fontWeight = param.rightButtonTextBlod)
                            }
                        }
                    }
                }
            }
        }
    }
}