# Compose 최적화

## compose에서 최적화란 ?

compose에서 최적화란 어떻게 smart하게 Recomposition을 하느냐를 말을 하는것이다.  
( 컴포지션 함수에서 데이터가 변경될 때 변경된 데이터에 해당하는 함수만 재호출하여 UI에 일부분만 업데이트 하는 방식)  
➔ 불필요한 부분(계산) 없이 필요한 부분만을 다시 그리기 때문에 성능 측면에서 엄청난 효율이 있다.

<br>

## smart Recomposition : 성능 향상을 위해 변경된 부분만 다시 그리기

<br>

## smart Recomposition을 이해하기 위한 3가지 지식



### Lifecycle(수명주기)
  - Composable  
  Composable 함수에서 사용되는 어노테이션이자 Jetpack Compose를 구성하는 함수의 이름  
  선언형 UI를 구성하는 기본 단위
  - Composition  
  Composable 함수가 화면에 그려지기 위한 전체의 과정  
  Recomposition과 비교하면 제일 처음 1회 그려지는 과정
  - Recomposition  
  처음 그려진 화면을 바꾸는 과정  
  이전의 계산한 값을 재사용하면서 UI 전체를 그리지 않고도 UI 일부를 업데이트 할 수 있음.  
  최적화 필수.


### Rendering Phase(렌더링 단계/Composition의 원리)
![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/6c52743d-519e-47d6-adb4-8f500eff378b)  
개발자가 선언형 코드를 통해 어떻게 UI를 그릴 수 있는 이유는 Data를 UI로 변환을 하게끔 만들어주기 때문이다.  
이렇게 Data를 통해 UI를 그리는 코드는 Composition -> Layout -> Drawing 의 3가지 단계를 거쳐서 실제 UI로 변환이 되는 것이다.  
각 단계의 영향  
  - Composition(What) : 처음으로 무엇을 그릴 지 결정  
  실제 코드를 UI Tree 구조(상태 + 로직 정보를 가진 Layout Node -> UI Tree 구조)로 변환을 한다.  
  Modifer에 정보를 바탕으로 레이아웃 노드들이 생성된다. ==> 이 부분을 최적화하는것이 smart Recomposition 하는데 기법 중 하나가 된다.  
  ![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/fdd76483-78f0-4be5-a18a-50ef7b9aedc3)
  - Layout(Where) : 각 요소가 한 화면상 어디에 그려질 지 결정  
  UI Tree 구조를 탐색하면서 화면에 어떤 위치에 각 레이아웃 노드를 배치를 할 지 결정한다.  
  자식 측정 -> 본인 크기 측정(자식이 있는지 없는 지 여부 확인) -> 자식 배치 라는 알고리즘을 통해서 각 요소가 한 화면상 어디에 그려질 지를 결정하는것임.  
  중요한 점은 기존의 xml 기반의 중첩되고 복잡한 UI를 구성을 할 때 느려지는 현상을 Compose 에서는 아무리 복잡한 UI를 구성하더라도 모든 Layout 노드를 오직 한번만 방문하기 때문에 느려짐 및 성능이 매우 좋다.
  ![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/34d967a7-0818-4512-a07b-eacb940482f7)  
  ![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/85db79b1-892c-4148-9e79-52a3bd6630ff)
  - Drawing(How) : 화면에 어떻게 그릴 지 결정
  이전 단계인 Layout 단계에서 이미 모든 Node에 대한 위치가 확정 되었기(Drawing단계에서도 위치를 바꾸는 코드는 있긴함) 때문에 Drawing 단계에서는 순서대로 하나하나 노드를 방문을 하면서 그리게 된다.  
  ![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/4f9ec938-bc53-4372-87fc-78dd6b6ae778)

<br>

### Satbility(안정성)

만약 컴포저블 함수의 콜사이트나 실행순서가 같다면(즉, 같은 컴포넌트라면) 리컴포지션이 필요한 지 아닌지 Compose가 어떻게 알 수 있을까??  
==> Parameter(매개변수)를 통해서 여러 상태 값들을 받아 어떤 상태들을 가지는지에 따라서 리컴포지션이 필요한 지  아닌지 판단할 수 있다!!  
  - 매개변수의 3가지 안정성 유형(컴포저블 함수 매개변수의 클래스 타입)
    - Unstable  
    데이터 변경 가능, 변경시 컴포지션에서 추적 불가!
    (컬렉션 함수, List, Set, Map 등등)
    - Stable  
    데이터 변경 가능, 변경시 컴포지션에서 추적 가능!
    (Unstable한 컬렉션 함수를 대응하기 위한 ImmutableList 개발됨, 만약 Unstable한 List를 Stable 하게 사용하고 싶다면 @Stable 이라는 어노테이션을 통해 사용 가능함.)
    - Immutable  
    안정적인 데이터로 취급, 원시타입(String, Int, Float)  
    (만약 Unstable한 List를 Immutable 하게 사용하고 싶다면 @Immutable 이라는 어노테이션을 통해 사용 가능함.)
    ![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/35a4fe1c-5efa-4882-aded-4fe27a61a274)  
    한가지로 예로 val 로 선언한 경우는 Immutable 한데 var로 선언한 경우는 Unsatble 하다.  
    그렇기에 val는 smart Recomposition의 대상이 되어 Recomposition 대상이 되지 않고, var로 선언을 할 경우에는 다른것들이 바뀌었을때 smart Recomposition을 하지 못해 해당 composable 함수는 무조건 Recomposition의 대상이 된다.  
    가장 중요한 점은 stable 유형으로 클래스를 생성을 하여 다른 composable 함수에 의해 Recomposition이 발생할 때 Recomposition을 피해야 한다.!!

    ![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/bbfc963e-4184-472d-90a8-ee823a1638c1)


<br> 

## 실제 최적화 예시
만약 Cloumn 블럭 안에 for 문을 돌려 어떠한 Composable 함수를 여러번 호출한다고 했을 때 마지막에 추가될 때는 문제가 되질 않지만, 중간에 삭제한다거나 추가를 한다면 전체 리스트를 새롭게 다시 그릴것이고 이것은 성능 문제가 있을 것이다.  
그렇기에 smart Recomposition을 사용하여 바뀐 부분만 변경이 될 수 있는 작업을 해줘야하는 것이다.  
  [최적화 X]  
  ![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/2563607d-5937-4e47-afd1-e2c1dfe0a110)  
  [최적화 O]  
  compose에서는 column에서는 key라는 함수/lazyColumn 에서는 매개변수 items라는 함수를 통해 고유한 값을 비교를 해서 성능을 최적화 시킬 수 있다.
  ![](https://github.com/meenjoon/JetpackComposeStudy/assets/88024665/cdff85ce-0b3b-4bc6-b4fc-8b8ea3526e7f)  


