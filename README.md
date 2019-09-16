# todo-project

### 1. 요구사항

* 새로운 TODO(제목과 내용)를 작성할 수 있다.
* TODO 목록을 볼 수 있다.
* TODO 항목의 제목과 내용을 수정할 수 있다.
* TODO 항목을 삭제할 수 있다.
* 사용자의 선택에 의해 TODO에는 마감 기한을 넣을 수 있다.
* TODO 항목의 우선순위를 설정 및 조절할 수 있다.
* TODO 항목에 대한 완료 처리를 할 수 있다.


### 2. Frontend build Setup

 1. npm 설치
 ``` bash
 $ yum install npm
 ```
 
 2. 프로젝트 빌드
``` bash
# frontend 폴더로 이동
$ cd fe

# package 설치
$ npm install

# module 추가
$ npm --save install vue bootstrap bootstrap-vue vue-datetime vue-datetime-picker vue-star-rating moment vue-momentjs axios luxon

# build
$ npm run build

# http://localhost:8888 로 접속해서 확인
$ npm run dev

```
