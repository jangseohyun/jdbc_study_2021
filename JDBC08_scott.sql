SELECT USER
FROM DUAL;
--==>> SCOTT

SELECT *
FROM TBL_MEMBER
ORDER BY SID;
/*
1	������	010-1111-1111
2	�̻���	010-4444-4444
3	������	010-2222-2222
4	�弭��	010-3333-3333
5	������	010-5555-5555
6	������	010-6666-6666
7	������	010-7777-7777
*/


--�� CallableStatement �ǽ��� ���� ���ν��� �ۼ�
--   ���ν��� ��: PRC_MEMBERINSERT
--   ���ν��� ���: TBL_MEMBER ���̺� �����͸� �Է��ϴ� ���ν���
CREATE OR REPLACE PROCEDURE PRC_MEMBERINSERT
( VNAME  IN TBL_MEMBER.NAME%TYPE
, VTEL   IN TBL_MEMBER.TEL%TYPE
)
IS
    VSID    TBL_MEMBER.SID%TYPE;
BEGIN
    -- ���� SID�� �ִ밪 ������
    SELECT NVL(MAX(SID),0) INTO VSID FROM TBL_MEMBER;
    
    -- ������ �Է�
    INSERT INTO TBL_MEMBER(SID,NAME,TEL) VALUES((VSID + 1),VNAME,VTEL);
    
    -- Ŀ��
    COMMIT;
END;
--==>> Procedure PRC_MEMBERINSERT��(��) �����ϵǾ����ϴ�.


--�� ������ ���ν��� �׽�Ʈ(Ȯ��)
EXEC PRC_MEMBERINSERT('�̻�ȭ','010-1212-3434');


--�� CallableStatement �ǽ��� ���� ���ν��� �ۼ�
--   ���ν��� ��: PRC_MEMBERSELECT
--   ���ν��� ���: TBL_MEMBER ���̺��� �����͸� �о���� ����� ���ν���
--   �� ��SYS_REFCURSOR�� �ڷ����� �̿��Ͽ� Ŀ�� �ٷ��
CREATE OR REPLACE PROCEDURE PRC_MEMBERSELECT
( VRESULT   OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN VRESULT FOR
        SELECT SID, NAME, TEL
        FROM TBL_MEMBER
        ORDER BY SID;
    --CLOSE VRESULT;
END;
--==>> Procedure PRC_MEMBERSELECT��(��) �����ϵǾ����ϴ�.