SELECT *
FROM TBL_SCORE;

INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT) VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)
;

UPDATE TBL_SCORE SET NAME = ?, KOR = ?, ENG = ?, MAT = ? WHERE SID = ?
;

DELETE FROM TBL_SCORE WHERE SID = ?
;

SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3.0 AS AVG
     , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
FROM TBL_SCORE
WHERE NAME = '�弭��'
ORDER BY SID ASC;

SELECT SID, NAME, KOR, ENG, MAT, (KOR+ENG+MAT) AS TOT, (KOR+ENG+MAT)/3.0 AS AVG
     , RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK
FROM TBL_SCORE
WHERE NAME = ?
ORDER BY SID ASC;

SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

CREATE SEQUENCE SCORESEQ
NOCACHE;