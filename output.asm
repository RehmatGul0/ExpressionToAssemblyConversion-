INCLUDE Irvine32.inc
.code
main PROC
MOV AX,1
PUSH AX
MOV AX,2
PUSH AX
POP AX
POP BX
ADD AX,BX
PUSH AX
call WriteDec
exit
main ENDP
END main
