MVP Pattern Sample
==================

해당 Repository는 Android의 MVP Pattern을 학습 및 연습하기 위한 Repository 입니다.

Branches
--------
* master
  * 최종 결과물에 대한 병합.
* mvc
  * 일반적인 android 개발 시 사용되는 mvc pattern.
* mvp-view-presenter
  * mvc pattern에서 view와 presenter를 분리.
* mvp-adapter
  * adapter와 관련된 코드 분리.
  * adapter가 가지는 특성인 view / model 관련된 부분은 별도의 contract로 분리.
  * adapter 접근을 위해 presenter에서 view를 한단계 더 거쳐야 되는 불필요한 호출을 제거하여, presenter에서 adapter로 바로 접근 가능하도록 하는 방법.
* mvp-model
  * mvc pattern에서 model을 분리.
* mvp-base
  * mvp pattern에서의 base class / interface 구현.