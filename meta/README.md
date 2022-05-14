# Pokemon Metadata

## Overview

Pokemon metadata는 Pokemon API의 응답을 위해 참조하는 데이터베이스로, 각 테이블은 CSV (without header) 형식으로 제공됩니다.

이 데이터베이스는 [PokeAPI data](https://github.com/PokeAPI/pokeapi/tree/master/data/v2/csv)를 편집하여 작성되었습니다.

## Tables

### POKEMON

Pokemon의 정보

| Column | Description | Type | Example |
|--------|-------------|------|---------|
| id | Pokemon의 번호 | Integer | 48 |
| identifier | Pokemon의 구분명<br />(ASCII 형식 영문명) | String | "venonat" |
| name | 이름 | String | "콘팡" |
| height | 표준 신장 | Integer | 10 |
| weight | 표준 체중 | Integer | 300 |
| base_experience | 기준 경험치 | Integer | 61 |

### TYPES

유형 정보

| Column | Description | Type | Example |
|--------|-------------|------|---------|
| id | 유형 번호 | Integer | 7 |
| identifier | 유형 구분명<br />(ASCII 형식 영문명) | String | "bug" |
| name | 이름 | String | "벌레" |

### ABILITIES

능력 정보

| Column | Description | Type | Example |
|--------|-------------|------|---------|
| id | 능력 번호 | Integer | 14 |
| identifier | 능력 구분명<br />(ASCII 형식 영문명) | String | "compound-eyes" |
| name | 이름 | String | "복안" |

### POKEMON_TYPES

Pokemon의 id별 유형 관계

| Column | Description | Type | Example |
|--------|-------------|------|---------|
| pokemon_id | Pokemon 번호 | Integer | 48 |
| type_id | 유형 번호 | Integer | 7 |
| slot | 유형 우선도 (1-based index) | Integer<br />(1 - 2) | 1 |

### POKEMON_ABILITIES

Pokemon의 id별 기본 습득 능력 관계

| Column | Description | Type | Example |
|--------|-------------|------|---------|
| pokemon_id | Pokemon 번호 | Integer | 48 |
| abilitiy_id | 능력 번호 | Integer | 14 |
| slot | 습득 슬롯 (1-based index) | Integer<br />(1 - 3) | 1 |

### BANNED_LIST

조회가 금지된 Pokemon 목록. 이 목록에 있는 Pokemon에 대해서는 정보 요청을 거부합니다.

| Column | Description | Type | Example |
|--------|-------------|------|---------|
| pokemon_id | Pokemon 번호 | Integer | 137 |
