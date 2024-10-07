# 나o만 - AI 기반 사진 공유 클라우드 서비스
・ 공유 그룹을 만들어 대규모 사진 업로드&다운로드 할 수 있어요.
<br>
・ AI가 분류해 준 <code>나만</code> 나온 사진만 다운 받을 수 있어요.
<br>
・ 안건을 만들어 투표 받을 수 있어요. (ex,프사 추천 & 웃긴 사진)
<br>
<div>
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=Kotlin&logoColor=white">
  <img src="https://img.shields.io/badge/jetpack_compose-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white">
</div>
<table>
  <tr>
    <td align="center"><img width="150" alt="na-o-man-한건희" src="https://github.com/user-attachments/assets/e7f9feda-04c8-4dda-8426-4173bac0dcb8"></td>
    <td align="center"><img width="150" alt="na-o-man-백종원" src="https://github.com/user-attachments/assets/7264840a-7b59-43ce-88fd-c4d6f22cd2c0"></td>
    <td align="center"><img width="150" alt="na-o-man-고민균" src="https://github.com/user-attachments/assets/af546406-fca2-48c6-a7d0-6cc8a34dc091"></td>
  </tr>
  <tr>
    <td align="center"><a href="https://github.com/hangunhee39">⭐️한건희</a></td>
    <td align="center"><a href="https://github.com/BAEK0111">백종원</a></td>
    <td align="center"><a href="https://github.com/skyblue1232">고민균</a></td>
  </tr>
  <tr>
    <td align="center"> 프로젝트 설계( MVVM+MVI패턴, Hilt, ..) <br> Sign <br> MyPage <br> AddMain <br> PhotoList <br> AgendaAdd <br> VoteDetail <br> Image-Up/Download(Background) </td>
    <td align="center"> Home <br> Alarm <br> GroupFolder <br> VoteHome </td>
    <td align="center"> GroupAdd <br> GroupJoin </td>
  </tr>
</table>
<br>

## 시연영상
[![Video Label](http://img.youtube.com/vi/KuYXcd8srDA/0.jpg)](https://youtu.be/KuYXcd8srDA?si=kQGUw3UE9g7H3I9Y)
<br>

## SCREENSHOTS
|       나       |                                                              o                                                              |                                                              만                                                              |                                                              !                                                              |     
|:-------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|
|  <img width="150px" src="https://github.com/user-attachments/assets/f5deab61-bfeb-4eda-834e-7ee3ecb52217"/> | <img width="150px" src="https://github.com/user-attachments/assets/8bd9003d-7b52-49cf-872e-55fd90a81139"/>  | <img width="150px" src="https://github.com/user-attachments/assets/32590fa6-b39d-491d-8933-2f173e9dca3f"/> | <img width="150px" src="https://github.com/user-attachments/assets/eab52228-69b2-4ffe-87e7-079307dacebc"/>  |   
|  <img width="150px" src="https://github.com/user-attachments/assets/a3a4d765-1f43-4a2b-9ff0-e7eae2e870e7"/> | <img width="150px" src="https://github.com/user-attachments/assets/d2eb54a9-5ad7-4f08-9dbe-01a56e35263f"/> | <img width="150px" src="https://github.com/user-attachments/assets/8a474927-2740-4723-baf8-f10a147372a0"/> | <img width="150px" src="https://github.com/user-attachments/assets/e7e1e89a-1f72-4579-b015-a3b88aa8373f"/>  |
|  <img width="150px" src="https://github.com/user-attachments/assets/e8d670af-205b-4426-9d7e-b1e0d3c36bde"/> | <img width="150px" src="https://github.com/user-attachments/assets/f1acfc4f-917e-4553-8953-4268528064e7"/> | <img width="150px" src="https://github.com/user-attachments/assets/ff23fd0f-2079-4f4f-a29f-d5f1a6753b74"/> | <img width="150px" src="https://github.com/user-attachments/assets/183c7afb-293a-4005-a683-fa5028dcbf78"/> |

<br>


## Clean Architecture
- MVVM + MVI
<img width="829" alt="나0만 arch" src="https://github.com/user-attachments/assets/8babe269-dd53-4f4c-a170-f378a85ab6b3">

## Project Folder Structure
```plaintext
📁 na-o-man
....
├── data
│   ├── dto
│   │   ├── agenda
│   │   │   └── response
│   │   ├── auth
│   │   │   ├── request
│   │   │   └── response
│   │   ├── common
│   │   │   ├── request
│   │   │   └── response
│   │   ├── member
│   │   │   └── response
│   │   ├── notification
│   │   │   ├── request
│   │   │   └── response
│   │   ├── photo
│   │   │   ├── request
│   │   │   └── response
│   │   └── share_group
│   │       ├── request
│   │       └── response
│   ├── repository
│   └── source
│       └── remote
│           └── api
├── di
│   └── util
│       ├── auth
│       ├── data_store
│       ├── remote
│       ├── s3
│       └── work_manager
│           ├── enqueue
│           └── task
├── domain
│   ├── model
│   │   ├── agenda
│   │   ├── auth
│   │   ├── member
│   │   ├── notification
│   │   ├── photo
│   │   └── share_group
│   ├── repository
│   └── usecase
│       ├── agenda
│       ├── auth
│       ├── member
│       ├── notification
│       ├── photo
│       └── share_group
└── presentation
    ├── base
    ├── component
    │   ├── NoticeIcon
    │   ├── groupdetail
    │   ├── home
    │   ├── homeIcon
    │   ├── mypage
    │   ├── type
    │   ├── userIcon
    │   └── voteIcon
    ├── theme
    ├── ui
    │   ├── add
    │   │   ├── addgroup
    │   │   └── joingroup
    │   ├── detail
    │   │   ├── GroupDetailFolder
    │   │   ├── agenda
    │   │   ├── photo_list
    │   │   ├── vote
    │   │   └── vote_detail
    │   ├── main
    │   │   ├── add_main
    │   │   ├── alarm
    │   │   ├── home
    │   │   └── mypage
    │   └── sign
    │       └── signin
    └── util
