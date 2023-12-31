# Recomposition

## Composition
- 앱의 UI를 설명, 컴포저블을 호출해 생성
- UI를 기술하는 컴포저블의 트리 구조
- 초기 컴포지션 : UI를 그리기 위헤 호출한 컴포저블을 추적

## Recomposition
- 상태가 바뀌었을 때 예약되며 (0회 이상) 변경점을 반영하려 컴포지션을 업데이트 함(수정하는 유일한 방법) 
- 쉽게 말해, param이 (data) 변경되었을때 이를 Compose가 확인하여 화면을 재구성하는것
- state의 변경이 없어 업데이트가 일어나지 않으면 Recomposition은 0회라고 말한다.
- State< T>가 변경되면 리컴포지션이 트리거(변경) 됨
- State< T>를 읽는 컴포저블과, 여기에서 호출되는 컴포저블이 대상이 된다.
- 모든 입력이 안정적이고 변경되지 않았으면 건너뛸 수 있다.
  - 안정적인 타입 
    - 두 인스턴스 equals 결과가 영원히 같은 경우
    - 공개 프로퍼티가 변경되면 컴포지션에 알려야 함
    - 모든 공개 프로퍼티는 안정적이어야 함  
      
  - 안정적이다고 추론할 수 없는 경우 @Stable을 표기해야 한다.

  - @Stable이 표기되지 않아도 Compose 컴파일러가 안정적이다고 간주하는 공통 타입들
    - 모든 기본자료형(프리미티브)) 타입: Boolean, Int, Long, Float 등
    - 문자열
    - 모든 함수 타입(람다)
    - MutableState는 안정적( State의 value 프로퍼티 값 변경이 알려지기 때문에 컴포지션에게 리컴포지션이 필요하다고 알려진다. )
- 리컴포지션에서 모든것을 변경하지 않고 바뀐것만을 변경한다는 점이 중요하다.

  - 리컴포지션의 행복한 경우 : Column 안에 여러개의 컴포넌트가 존재했고, 이 상황에서 마지막 컴포넌트 인스턴스가 추가가 되면 그 마지막 컴포넌트 인스턴스만 업데이트가 이루어진다.
  - 리컴포지션의 불행한 경우 : Column 안에 여러개의 컴포넌트가 존재했고, 이 상황에서 첫번째 컴포넌트 인스턴스가 삽입(추가)되면 새롭게 추가된 인스턴스와 함께 나머지 인스턴스도 업데이트가 이루어진다.(Jetpack Compose의 한계)
    - ==> 이러한 한계를 해결하기 위해 Key 컴포저블 지원 내장 컴포저블을 지원한다.  
    보통 LazyColumn과 같은 경우에 사용이 된다.