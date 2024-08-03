package com.hgh.na_o_man.presentation.component.voteIcon

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hgh.na_o_man.presentation.theme.LightWhite
import com.hgh.na_o_man.presentation.theme.SteelBlue

@Composable
fun DropDownMenu(isVisible: Boolean, onDismiss: () -> Unit) {
    var selectedNames by remember { mutableStateOf(mutableListOf<String>()) }
    var names by remember { mutableStateOf(mutableListOf("황지원", "김똘똘", "박을순", "Others")) }
    var customName by remember { mutableStateOf("") }
    var showAddNameField by remember { mutableStateOf(false) }
    var scrollState = rememberScrollState()

    // AnimatedVisibility 사용
    AnimatedVisibility(visible = isVisible) {
        Box(
            modifier = Modifier
                .clickable { onDismiss() } // 외부 클릭 시 드롭다운 닫기
        ) {
            Column(
                modifier = Modifier
                    .border(1.dp, LightWhite, RoundedCornerShape(20.dp))
                    .clip(RoundedCornerShape(20.dp))
                    .background(LightWhite.copy(alpha = 0.6f))
                    .padding(8.dp)
                    .width(200.dp)
                    .heightIn(max = 250.dp)
                    .verticalScroll(scrollState)
            ) {
                // 현재 인덱스에 따라 이름을 표시
                names.forEach { name ->
                    Text(
                        text = name,
                        color = if (selectedNames.contains(name)) Color.Gray else Color.Unspecified,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                if (name != "Others") { // "Others"는 선택하지 않음
                                    if (selectedNames.contains(name)) {
                                        selectedNames.remove(name) // 이미 선택된 경우 선택 해제
                                    } else {
                                        selectedNames.add(name) // 선택된 이름 업데이트
                                    }
                                } else {
                                    showAddNameField = !showAddNameField // Add Name 필드 토글
                                }
                            }
                    )
                }

                // "Others" 클릭 시 New 드롭다운 메뉴 표시
                if (showAddNameField) {
                    Column(
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.2f))
                            .padding(8.dp)
                    ) {
                        TextField(
                            value = customName,
                            onValueChange = { customName = it },
                            label = { Text("이름 입력") },
                            modifier = Modifier.padding(8.dp)
                        )
                        Button(
                            onClick = {
                                if (customName.isNotEmpty()) {
                                    names.add(customName)
                                    customName = ""
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = SteelBlue), // SteelBlue 색상
                            modifier = Modifier.padding(8.dp),
                        ) {
                            Text("추가")
                        }
                    }
                }

                // "전체" 버튼 추가
                Text(
                    text = "전체",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            selectedNames.clear() // 이전 선택 해제
                            selectedNames.addAll(names.filter { it != "Others" }) // 모든 이름 선택 (Others 제외)
                        }
                )

                // "없음" 버튼 추가
                Text(
                    text = "없음",
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            selectedNames = mutableListOf("없음") // 선택된 이름 업데이트
                            onDismiss() // "없음" 선택 시 드롭다운 닫기
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDropDownMenu() {
    DropDownMenu(isVisible = true) { /* dismiss action */ }
}
