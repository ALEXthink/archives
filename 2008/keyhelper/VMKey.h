//���ų��� ʮ������ֵ ָ����������̰���
//		;		//		;		//		;		//		;		// VMKey.h
#include <afxwin.h>
const long VK_0=48;
const long VK_1=49;
const long VK_2=50;
const long VK_3=51;
const long VK_4=52;
const long VK_5=53;
const long VK_6=54;
const long VK_7=55;
const long VK_8=56;
const long VK_9=57;
const long VK_A=65;
const long VK_B=66;
const long VK_C=67;
const long VK_D=68;
const long VK_E=69;
const long VK_F=70;
const long VK_G=71;
const long VK_H=72;
const long VK_I=73;
const long VK_J=74;
const long VK_K=75;
const long VK_L=76;
const long VK_M=77;
const long VK_N=78;
const long VK_O=79;
const long VK_P=80;
const long VK_Q=81;
const long VK_R=82;
const long VK_S=83;
const long VK_T=84;
const long VK_U=85;
const long VK_V=86;
const long VK_W=87;
const long VK_X=88;
const long VK_Y=89;
const long VK_Z=90;


long KeyList(CString string)
{
if(string=="MouseLeft") return VK_LBUTTON;		
else if(string=="MouseRight") return VK_RBUTTON;		
else if(string=="MouseMid") return VK_MBUTTON;
else if(string=="BACKSPACE") return VK_BACK;	//08		;		// �� 
else if(string=="TAB") return VK_TAB;		//09		;		// �� 
else if(string=="CLEAR") return VK_CLEAR		;		//0C		;		// �� 
else if(string=="ENTER") return VK_RETURN		;		//0D		;		// �� 
//else if(string=="SHelse") return VK_SHelse  ;		//10		;		// �� 
else if(string=="CTRL") return VK_CONTROL		;		//11		;		// �� 
else if(string=="ALT") return VK_MENU		;		//12		;		// �� 
else if(string=="PAUSE") return VK_PAUSE 		;		// �� 
else if(string=="CAPSLOCK") return VK_CAPITAL		;		//14		;		// ��		;		//
else if(string=="ESC") return VK_ESCAPE		;		//1B		;		// �� 
else if(string=="SPACEBAR") return VK_SPACE		;		//20		;		//		;		//
else if(string=="PAGEUP") return VK_PRIOR		;		//21		;		// ��		;		//
else if(string=="PAGEDOWN") return VK_NEXT		;		//22		;		// �� 
else if(string=="END") return VK_END		;		//23		;		// �� 
else if(string=="HOME") return VK_HOME		;		//24		;		// �� 
else if(string=="LEFTARROW") return VK_LEFT		;		//25		;		// ��		;		//
else if(string=="UPARROW") return VK_UP		;		//26		;		// ��		;		//
else if(string=="RIGHTARROW") return VK_RIGHT		;		//27		;		// ��		;		//
else if(string=="DOWNARROW") return VK_DOWN		;		//28		;		// ��		;		//
else if(string=="SELECT") return VK_SELECT		;		//29		;		// �� 
else if(string=="EXECUTE") return VK_EXECUTE		;		//2B		;		// ��		;		//
else if(string=="PRSCREEN") return VK_SNAPSHOT		;		//2C		;		//��������Windows 3.0���Ժ�汾�� 
else if(string=="INS") return VK_INSERT		;		//2D		;		// ��		;		//
else if(string=="DEL") return VK_DELETE		;		//2E		;		// ��		;		//
else if(string=="HELP") return VK_HELP		;		//2F		;		// ��		;		//
else if(string=="0") return VK_0		;		//30		;		//0 ��		;		//
else if(string=="1") return VK_1		;		//31		;		//1 ��		;		//
else if(string=="2") return VK_2		;		//32		;		//2 ��		;		//
else if(string=="3") return VK_3		;		//33		;		//3 ��		;		//
else if(string=="4") return VK_4		;		//34		;		//4 ��		;		//
else if(string=="5") return VK_5		;		//35		;		//5 ��		;		//
else if(string=="6") return VK_6		;		//36		;		//6 ��		;		//
else if(string=="7") return VK_7		;		//37		;		//7 ��		;		//
else if(string=="8") return VK_8		;		//38		;		//8 ��		;		//
else if(string=="9") return VK_9		;		//39		;		//9 �� 
else if(string=="A") return VK_A		;		//41		;		//A ��		;		//
else if(string=="B") return VK_B		;		//42		;		//B ��		;		//
else if(string=="C") return VK_C		;		//43		;		//C ��		;		//
else if(string=="D") return VK_D		;		//44		;		//D ��		;		//
else if(string=="E") return VK_E		;		//45		;		//E ��		;		//
else if(string=="F") return VK_F		;		//46		;		//F ��		;		//
else if(string=="G") return VK_G		;		//47		;		//G ��		;		//
else if(string=="H") return VK_H		;		//48		;		//H �� 
else if(string=="I") return VK_I		;		//49		;		//I �� 
else if(string=="J") return VK_J		;		// 4A 		;		// J �� 
else if(string=="K") return VK_K		;		// 4B 		;		// K �� 
else if(string=="L") return VK_L 		;		// 4C 		;		// L ��
else if(string=="M") return VK_M 		;		// 4D 		;		// M ��
else if(string=="N") return VK_N		;		// 4E 		;		//��
else if(string=="O") return VK_O 		;		// 4F 		;		// O ��
else if(string=="P") return VK_P 		;		// 		;		//50 		;		// P ��
else if(string=="Q") return VK_Q 		;		// 51 		;		// Q ��
else if(string=="R") return VK_R 		;		// 52 		;		// R ��
else if(string=="S") return VK_S 		;		//53 		;		//S ��
else if(string=="T") return VK_T 		;		//54 		;		//T ��
else if(string=="U") return VK_U 		;		// 55 		;		// U ��
else if(string=="V") return VK_V 		;		// 56 		;		// V ��
else if(string=="W") return VK_W 		;		// 57 		;		// W ��
else if(string=="X") return VK_X 		;		// 58 		;		//X ��
else if(string=="Y") return VK_Y 		;		// 59 		;		// Y ��
else if(string=="Z") return VK_Z 		;		// 5A 		;		// Z ��
//else if(string=="Left Windows") return VK_LWI 		;		// 5B 		;		// Left Wi dows �� (Microsoft��Ȼ����)
//else if(string=="Right Windows") return VK_RWI 		;		// 5C 		;		// Right Wi dows �� (Microsoft��Ȼ����)
else if(string=="Applications") return VK_APPS 		;		// 5D 		;		// Applicatio s �� (Microsoft��Ȼ����)
else if(string=="NUM0") return VK_NUMPAD0 		;		// 60 		;		// ����С�����ϵ� 0 ��
else if(string=="NUM1") return VK_NUMPAD1 		;		// 61 		;		// ����С�����ϵ� 1 ��
else if(string=="NUM2") return VK_NUMPAD2 		;		// 62 		;		// ����С�����ϵ� 2 ��
else if(string=="NUM3") return VK_NUMPAD3		;		//63		;		//����С�����ϵ� 3 ��
else if(string=="NUM4") return VK_NUMPAD4		;		//64		;		//����С�����ϵ� 4 ��
else if(string=="NUM5") return VK_NUMPAD5		;		//65		;		//����С�����ϵ� 5 ��
else if(string=="NUM6") return VK_NUMPAD6		;		//66		;		//����С�����ϵ� 6 ��
else if(string=="NUM7") return VK_NUMPAD7 		;		// 67		;		//����С�����ϵ� 7 ��
else if(string=="NUM8") return VK_NUMPAD8 		;		// 68����С�����ϵ� 8 ��
else if(string=="NUM9") return VK_NUMPAD9 		;		// 69 		;		// ����С�����ϵ� 9 ��
else if(string=="Multiply") return VK_MULTIPLY 		;		// 6A		;		//Multiply ��
else if(string=="Add") return VK_ADD		;		//6B		;		//Add ��
else if(string=="Separator") return VK_SEPARATOR		;		//6C		;		//Separator ��
else if(string=="Subtract") return VK_SUBTRACT		;		//6D 		;		// Subtract ��
else if(string=="Decimal") return VK_DECIMAL 		;		// 6E 		;		// Decimal ��
else if(string=="Divide") return VK_DIVIDE 		;		// 6F 		;		// Divide ��
else if(string=="F1") return VK_F1 		;		// 70 		;		// F1 ��
else if(string=="F2") return VK_F2 		;		// 71 		;		// F2 ��
else if(string=="F3") return VK_F3 		;		// 72 		;		// F3 ��
else if(string=="F4") return VK_F4 		;		// 73 		;		// F4 ��
else if(string=="F5") return VK_F5 		;		// 74 		;		// F5 ��
else if(string=="F6") return VK_F6 		;		// 75 		;		// F6 ��
else if(string=="F7") return VK_F7 		;		// 76 		;		// F7 ��
else if(string=="F8") return VK_F8		;		//77 		;		// F8 ��
else if(string=="F9") return VK_F9 		;		// 78 		;		// F9 ��
else if(string=="10") return VK_F10 		;		// 79 		;		// F10 ��
else if(string=="11") return VK_F11		;		//7A 		;		// F11 ��
else if(string=="F12") return VK_F12		;		//7B 		;		// F12 ��
else if(string=="F13") return VK_F13 		;		// 7C 		;		// F13 ��
else if(string=="F14") return VK_F14		;		//7D		;		//F14 ��
else if(string=="F15") return VK_F15		;		//7E		;		//F15 ��
else if(string=="F16") return VK_F16		;		//7F		;		//F16 ��
else if(string=="F17") return VK_F17		;		//80H		;		//F17 ��
else if(string=="F18") return VK_F18		;		//81H		;		//F18 ��
else if(string=="F19") return VK_F19		;		//82H		;		//F19 ��
else if(string=="F20") return VK_F20 		;		// 83H		;		//F20 ��
else if(string=="F21") return VK_F21		;		//84H		;		//F21 ��
else if(string=="F22") return VK_F22		;		//85H		;		//F22 ��
else if(string=="F23") return VK_F23		;		//86H		;		//F23 ��
else if(string=="F24") return VK_F24		;		//87H		;		//F24 ��
else if(string=="NUMLOCK") return VK_NUMLOCK 		;		// 90 		;		//		;		//UM LOCK ��
else if(string=="SCROLL LOCK") return VK_SCROLL 		;		// 91 		;		// SCROLL LOCK ��
//else if(string=="Att") return VK_ATT 		;		// F6		;		//Att ��
else if(string=="CrSel") return VK_CRSEL		;		//F7		;		//CrSel ��
else if(string=="ExSel") return VK_EXSEL		;		//F8		;		//ExSel ��
else if(string=="Erase EOF") return VK_EREOF		;		//F9		;		//Erase EOF ��
else if(string=="Play") return VK_PLAY		;		//FA Play 		;		//Play ��
else if(string=="Zoom") return VK_ZOOM		;		//FB		;		//Zoom ��
else if(string=="PA1") return VK_PA1 		;		// FD 		;		// PA1 ��
else if(string=="Clear") return VK_OEM_CLEAR 		;		// FE 		;		// Clear ��		;		//		;		//		;		//		;		// 
else return 0;
}
