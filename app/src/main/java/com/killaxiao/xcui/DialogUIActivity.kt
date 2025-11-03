package com.killaxiao.xcui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.killaxiao.ui.dialog.GeneralTextDialog
import com.killaxiao.ui.dialog.XCGeneralDialog
import com.killaxiao.xcui.ui.theme.XCUITheme

class DialogUIActivity: ComponentActivity() {
    private var cGeneralDialogShow = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            mainUI()
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun mainUI(){
        XCUITheme {
            Surface(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
                FlowRow(horizontalArrangement = Arrangement.spacedBy(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Button(onClick = {
                        cGeneralDialogShow.value = true
                    }, modifier = Modifier.padding(8.dp)) {
                        Text(text = stringResource(R.string.general_text_dialog))
                    }
                }

                GeneralTextDialog(cGeneralDialogShow, XCGeneralDialog().also {
                    it.titleText = "温馨提示"
                    it.contentText = "这是一段提示内容"
                }, leftBtnClick = {
                    Toast.makeText(this,"click left button", Toast.LENGTH_SHORT).show()
                    cGeneralDialogShow.value =false
                }, rightBtnClick = {
                    Toast.makeText(this,"click right button", Toast.LENGTH_SHORT).show()
                    cGeneralDialogShow.value =false
                })
            }
        }
    }

    @Preview(showBackground = true, device = Devices.NEXUS_5)
    @Composable
    fun Preview() {
        mainUI()
    }
}