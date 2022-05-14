# Pokemon API DC

Pokemon API DC는 REST API 서버 구현 실습을 위한 [PokeAPI](https://pokeapi.co/)의 Dead copy입니다.

## Overview

Pokemon API DC는 요청한 ID에 해당하는 Pokemon에 대한 정보를 제공하는 REST API 서비스입니다.

## API (Endpoint)

### Pokemon

`pokemon_id`에 해당하는 Pokemon에 대한 정보를 요청합니다.

#### Request

##### Method & API Path

> GET /v1/pokemon/{pokemon_id}

##### Path variables

| Variable | Description | Type | Example |
|----------|-------------|------|---------|
| pokemon_id | 정보를 요청할 Pokemon의 번호 | Integer | 48 |

##### Request parameters

| Parameter | Description | Type | Example |
|-----------|-------------|------|---------|
| ts | 요청 시각의 Unix timestamp (seconds) | Long | 1651224648 |
| cks | Checksum | 7 bytes string (Hex digest) | e3c6c49 |

##### Sample request

```
GET /v1/pokemon/48?ts=1651224648&cks=e3c6c49
```

#### Response

JSON 형식의 HTTP body로 제공됩니다.

- Result code `2000 SUCCESS`를 제외하고는 `rc` 외의 attribute가 존재하지 않습니다.
- `404 NOT FOUND`는 응답 HTTP body가 없습니다.

##### Body attributes (JSON)

| Attribute | Description | Type                    | Example |
|-----------|-------------|-------------------------|---------|
| rc | 응답 결과 코드 (Result code) | Integer                 | 1000 |
| id | 정보를 제공하는 Pokemon의 번호 | Integer                 | 48 |
| name | Pokemon의 이름 | String                  | "콘팡" |
| height | Pokemon의 표준 신장 | Integer                 | 10 |
| weight | Pokemon의 표준 체중 | Integer                 | 300 |
| types | Pokemon의 유형 목록 (유형 우선도 순) | List\<String>           | ["벌레", "독"] |
| types[i] | Pokemon의 유형 | String                  | "벌레" |
| abilities | Pokemon의 기본 습득 능력 목록 (항상 3가지, 습득 슬롯 순) | List\<Optional\<String>> | ["복안", "색안경", "도주"] |
| abilities[i] | Pokemon의 기본 습득 능력 | Optional\<String>       | "복안" |

##### HTTP status codes & Result codes

| HTTP code | Result code (rc) | Description |
|-----------|------------------|-------------|
| 200 | 2000 | SUCCESS<br />요청이 정상 처리되어 정보를 제공합니다. |
| 200 | 2001 | CENSORED<br />이 요청에 대해서는 정보를 제공할 수 없습니다. |
| 400 | 4000 | BAD REQUEST<br />잘못된 요청입니다. (기타 사유) |
| 400 | 4001 | INVALID ID<br />잘못된 `pokemon_id`입니다. (형식 오류 또는 존재하지 않는 `pokemon_id`) |
| 400 | 4002 | NULL TIMESTAMP<br />`ts` 인자가 존재하지 않습니다. |
| 400 | 4003 | INVALID TIMESTAMP<br />잘못된 `ts`입니다. (형식 오류 또는 요청 timestamp 만료) |
| 400 | 4004 | NULL CHECKSUM<br />`cks` 인자가 존재하지 않습니다. |
| 400 | 4005 | INVALID CHECKSUM<br />잘못된 `cks`입니다. (Checksum 검증 실패) |
| 404 | - | NOT FOUND<br />존재하지 않는 Endpoint입니다. |
| 500 | 5000 | INTERNAL SERVER ERROR<br />서버에 오류가 발생했습니다. |

##### Sample response

###### 200 OK / 2000 SUCCESS

```
HTTP 200 OK
Content-Type: application/json; Charset: UTF-8
{
    "rc": 2000,
    "id": 48,
    "name": "콘팡",
    "height": 10,
    "weight": 300,
    "types": ["벌레", "독"],
    "abilities": ["복안", "색안경", "도주"]
}
```

###### 200 OK / 2001 CENSORED

```
HTTP 200 OK
Content-Type: application/json; Charset: UTF-8
{
    "rc": 2001
}
```

###### 400 BAD REQUEST / 4005 INVAILED CHECKSUM

```
HTTP 400 BAD REQUEST
Content-Type: application/json; Charset: UTF-8
{
    "rc": 4005
}
```

###### 404 NOT FOUND

```
HTTP 404 NOT FOUND
```

###### 500 INTERNAL SERVER ERROR / 5000 INTERNAL SERVER ERROR

```
HTTP 500 INTERNAL SERVER ERROR
Content-Type: application/json; Charset: UTF-8
{
    "rc": 5000
}
```

## Appendix

### Checksum

`cks` (Checksum) 은 API 요청의 무결성을 검증하는 수단으로써, `pokemon_id`, `ts`와 Endpoint를 해싱한 결과의 일부입니다.

#### Sample code (Python)

```
# For calculating the cks of '/v1/pokemon/48?ts=1651224648'

import hashlib

pokemon_id = 48
timestamp = 1651224648
endpoint = 'pokemon'

plain_text = str(pokemon_id) + str(timestamp) + endpoint

md5 = hashlib.md5()
md5.update(plain_text.encode('utf-8'))
checksum = md5.hexdigest()[:7]

print(checksum)
```

### Timestamp

`ts` (Timestamp) 는 API 요청의 시간 유효성을 검증하는 수단으로써, 초 단위의 UNIX timestamp입니다.

서버는 요청을 처리하는 시각의 60 - 0초 전의 `ts`를 가진 요청만을 처리합니다.

Timestamp 검증에 실패한 요청에 대해서는 `400 BAD REQUEST` / `4003 INVAILD TIMESTAMP`으로 응답합니다.
