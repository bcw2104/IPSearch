# 🚀 IPSearch

### 📄 개요
IP 정보 및 위치 조회 프로그램

### 🛠️ Open API
- Naver GeoLocation
- Naver Web Dynamic Map 

### 📜 요구 기능
- 사용자(Client)의 IP주소 표시 기능
- 사용자(Client)의 IP검색 기능
  * 검색된 IP의 위치 정보 표시 
  * 검색된 IP의 부가 정보 표시
  * 검색된 IP의 위치를 지도에 표시
- 사용자(Client)가 검색한 IP주소와 부가정보를 DB에 저장

### 📌 IP 검색 로직
1. 대상 IP를 DB에서 탐색
    * 존재할 경우 : 반환 후 로직 종료
    * 존재하지 않을 경우 : 2번 동작 수행
2. GeoLocation API를 통해 대상 IP 정보를 수집
3. 수집된 IP 정보를 반환 및 DB에 저장

### 📈 개발 순서
- Version 1 -> Naver GeoLocation 관련 서비스 구현 <strong>(완료)</strong>
  * 사용자(Client)의 IP주소 표시 기능
  * 사용자(Client)의 IP검색 기능 
  * UI 구현
- Version 2 -> Naver Web Dynamic Map 관련 서비스 구현 <strong>(완료)</strong>
  * 검색된 IP의 위치를 지도에 표시
  * UI 개선
- Version 3 -> DataBase
  * DB 연결 및 IP 데이터 수집, 반환 기능 구현

### :hammer: Tech
<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=HTML5&logoColor=white"/></a> 
<img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=CSS3&logoColor=white"/></a> 
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=JavaScript&logoColor=white"/></a>
<img src="https://img.shields.io/badge/jQuery-0769AD?style=flat-square&logo=jQuery&logoColor=white"/></a> 
<img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat-square&logo=Bootstrap&logoColor=white"/></a></br>
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat-square&logo=Spring&logoColor=white"/></a>
<img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white"/></a>
<img src="https://img.shields.io/badge/MariaDB-003545?style=flat-square&logo=MariaDB&logoColor=white"/></a>

