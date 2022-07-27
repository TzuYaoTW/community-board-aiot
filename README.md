# Community Board By Spring Boot

> 原先的資策會AIOT期末專案後端使用的是Java Servlet+JSP
>
> 此repository將之改寫為Spring Boot架構
> 
> 附上OpenAPI文件(ymal檔，api-docs.yaml)，可複製至 [https://editor.swagger.io/](https://editor.swagger.io/ "Swagger Editor 線上版")  觀看

# 開發環境

- 開發工具：intelliJ IDEA Ultimate：2020.3.3
- Java 11 (AdoptOpenJDK)
- MySql：8.0.17
- Chrome 擴充功能 Talend API Tester
### dependency 
- Spring Boot：2.3.7.RELEASE
- Swagger2 & ui 2.9.2
- lombok：1.18.20

# 使用Spring boot建構專案後端API
### 簡述專案包含的API

- 管理者(社區保全或警衛) CRUD

	1. 新增管理員資料
	2. 查詢管理員資料
	3. 修改管理員資料
	4. 刪除管理員資料

----------
- 社區公告欄(例如里長公告或停電停水等公告) CRUD

	1. 新增公告欄訊息
	2. 查詢公告欄訊息(可分類查詢)
	3. 修改公告欄訊息
	4. 刪除公告欄訊息

----------
- 住戶 CRUD
	1. 新增住戶資料
	2. 查詢住戶資料(模糊查詢姓名、分頁配置(limit、offset、count)、排序)
	3. 修改住戶資料
	4. 刪除住戶資料

----------
- 社區設施租借 CRUD
	1. 新增租借
	2. 查詢租借狀況(模糊查詢姓名、場地、日期、預定時間(ex: 10:00-12:00)、可排除過期預定)
	3. 修改租借
	4. 刪除租借

----------
- 包裹 CRUD
	1. 新增包裹
	2. 查詢包裹(可透過RFID領取包裹、模糊查詢住址找包裹，可排除已領取包裹、分頁配置(limit、offset、count)、排序)
	3. 修改包裹(可修改包裹狀態(轉變為已領取))
	4. 刪除包裹

----------
- RFID CRUD
	1. 新增RFID資料
	2. 查詢RFID資料
	3. 修改RFID資料
	4. 刪除RFID資料

----------
- 人臉辨識
	1. 接收人臉辨識結果(RaspberryPi)，並且回傳token
	2. 接收前端token，比對RaspberryPi的token是否一致，並且取得相對應住戶資料

----------
### 圖片示意

![](https://github.com/TzuYaoTW/community-board-aiot/blob/main/API%E6%96%87%E4%BB%B6%E7%A4%BA%E6%84%8F%E5%9C%96.png?raw=true)

### 持續新增中
