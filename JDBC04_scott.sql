SELECT USER
FROM DUAL;
--==>> SCOTT

TRUNCATE TABLE TBL_SCORE;
--==>> Table TBL_SCORE��(��) �߷Ƚ��ϴ�.

DROP SEQUENCE SCORESEQ;
--==>> Sequence SCORESEQ��(��) �����Ǿ����ϴ�.


--�� ������ ����
CREATE SEQUENCE SCORESEQ
NOCACHE;
--==>> Sequence SCORESEQ��(��) �����Ǿ����ϴ�.


--�� ������ �غ�
-- 1. ������ �Է� ������ ����
INSERT INTO TBL_SCORE(SID,NAME,KOR,ENG,MAT) VALUES(SCORESEQ.NEXTVAL,'ȫ�浿',90,80,70)
;
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

--2. ����Ʈ ��� ������ ����
SELECT SID,NAME,KOR,ENG,MAT,KOR+ENG+MAT AS TOT,(KOR+ENG+MAT)/3.0 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE ORDER BY SID ASC
;

--3. �ο� �� ��ȸ ������ ����
SELECT COUNT(*) AS COUNT FROM TBL_SCORE
;

--4. �̸� �˻� ������ ����
SELECT * FROM (SELECT SID,NAME,KOR,ENG,MAT,KOR+ENG+MAT AS TOT,(KOR+ENG+MAT)/3.0 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE NAME = 'ȫ�浿' ORDER BY SID ASC
;

--5. ��ȣ �˻� ������ ����
SELECT * FROM (SELECT SID,NAME,KOR,ENG,MAT,KOR+ENG+MAT AS TOT,(KOR+ENG+MAT)/3.0 AS AVG, RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK FROM TBL_SCORE) WHERE SID = 1 ORDER BY SID ASC
;

--6. ������ ���� ������ ����
UPDATE TBL_SCORE SET NAME = '��浿', KOR = 10, ENG = 10, MAT = 10 WHERE SID = 1
;
--==>> 1 �� ��(��) ������Ʈ�Ǿ����ϴ�.

COMMIT;
--==>> Ŀ�� �Ϸ�.

--7. ������ ���� ������ ����
DELETE FROM TBL_SCORE WHERE SID = 1;
--==>> 1 �� ��(��) �����Ǿ����ϴ�.

COMMIT;
--==>> Ŀ�� �Ϸ�.