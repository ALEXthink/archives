// Snake.cpp: implementation of the CSnake class.
//
//////////////////////////////////////////////////////////////////////

#include "stdafx.h"
#include "Snake.h"
#include "resource.h"

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

extern HINSTANCE hInst;

CSnake::CSnake()
{
	m_length=1;
	m_head=new CNode;
	m_tail=new CNode;
	m_head->m_next=m_tail;
	m_tail->m_former=m_head;

	m_head->x=15;
	m_head->y=15;
	m_tail->x=m_head->x;
	m_tail->y=m_head->y;
	m_headTrend=LEFT;
	m_issk=false;
}

CSnake::~CSnake()
{
	ReleaseDC(hWnd,m_bmDC);
	CNode *p=m_head;
	DeleteNode(m_length);
	delete m_head;
	delete m_tail;
}

bool CSnake::AddNode(int num)
{
	if(num<0) return false;

	for(int i=0;i<num;i++)
	{
		CNode *newnode=new CNode;

		//��������
		newnode->x=m_tail->x;
		newnode->y=m_tail->y;

		//��β��ǰ����
		//next	
		newnode->m_next=m_tail;
		m_tail->m_former->m_next=newnode;
		//former
		newnode->m_former=m_tail->m_former;
		m_tail->m_former=newnode;
	}
	m_length+=num;
	return false;
}

bool CSnake::DeleteNode(int num)
{
	if(num<0 || num>m_length) return false;

	for(int i=1;i<num;i++)
	{
		//β��ǰ����
		CNode *wantdel;
		wantdel=m_tail->m_former;	//ȡ����ɾ����

		wantdel->m_former->m_next=m_tail; //����ǰһ���ġ���"����
		m_tail->m_former=wantdel->m_former;//����β�͵�ǰһ��

		delete wantdel;
	}
	m_length-=num;
	return false;
}

int CSnake::GetLength()
{
	return m_length;
}

bool CSnake::SetInitLength(int len)
{
	if(len<0) return false;
	return AddNode(len);
}

CSnake::CSnake(int len)
{
	SetInitLength(len);
}

bool CSnake::IsEmpty()
{
	return m_length==0;
}


bool CSnake::MoveSnack(int pace)
{
	CNode Prim=*m_head;	//Prim�ƶ�ǰ�ڵ�λ��
	//�ƶ�ͷ
	if(!SwitchTrend(m_headTrend,m_head->x,m_head->y,pace))
	{
		return false;
	}
	//��β��ǰһ���ŵ� ͷ�ĺ�һ��
	CNode *mov=m_tail->m_former;
	//�õ�
	m_tail->m_former=mov->m_former;
	mov->m_former->m_next=m_tail;
	CNode Mov=*mov;
	//����
	mov->m_next=m_head->m_next;
	mov->m_former=m_head;
	
	m_head->m_next=mov;
	mov->m_next->m_former=mov;
	//��������
	mov->x=Prim.x;
	mov->y=Prim.y;
	//β��ǰ��
	m_tail->x=Mov.x;
	m_tail->y=Mov.y;

	return true;
}

ETrend CSnake::GetTrend()
{
	return m_headTrend;
}

ETrend CSnake::NotTrend()
{
	if(m_headTrend==LEFT	) return RIGHT;
	if(m_headTrend==RIGHT	) return LEFT;
	if(m_headTrend==UP		) return DOWN;
	if(m_headTrend==DOWN	) return UP;
	return NOTREND;
}

bool CSnake::SwitchTrend(ETrend trend, int &x, int &y,int pace)
{
	switch(trend)
	{
	case LEFT:
		x-=pace;
		break;
	case RIGHT:
		x+=pace;
		break;
	case UP:
		y-=pace;
		break;
	case DOWN:
		y+=pace;
		break;
	default :
		return false;
		break;
	}
	return true;
}

bool CSnake::GetSnack(int *x, int *y)
{
	CNode *p=m_head;
	for(int i=0;i<=m_length;i++)
	{
		x[i]=p->x;
		y[i]=p->y;
		p=p->m_next;
	}
	x[m_length+1]=m_tail->x;
	y[m_length+1]=m_tail->y;
	return true;
}

void CSnake::DrawSnack(HDC hDC,CPanel *pPanel)
{
	//ȡ�����нڵ�λ��
	int *x=new int[m_length+2];
	int *y=new int[m_length+2];
	GetSnack(x,y);
	//����
	HBITMAP oldbmp;

	//ͷ
	int hl=pPanel->GetGcx() * x[0];
	int ht=pPanel->GetGcy() * y[0];
	int hcx=pPanel->GetGcx();
	int hcy=pPanel->GetGcy();
	if(m_issk)
	{
		oldbmp=(HBITMAP)SelectObject(m_bmDC,m_shrunken);
		m_issk=false;
	}
	else
	{
		oldbmp=(HBITMAP)SelectObject(m_bmDC,m_headbmp);
		m_issk=true;
	}
	TransparentBlt(hDC,hl,ht,hcx,hcy,m_bmDC,0,0,47,47,RGB(255,0,0));

	SelectObject(m_bmDC,m_nodebmp);
	//�ڵ�
	for(int i=1;i<=m_length+1;i++)
	{
		int nl=pPanel->GetGcx() * x[i];
		int nt=pPanel->GetGcy() * y[i];
		int ncx=pPanel->GetGcx();
		int ncy=pPanel->GetGcy();
		TransparentBlt(hDC,nl,nt,ncx,ncy,m_bmDC,0,0,47,47,RGB(255,0,0));
	}
	SelectObject(m_bmDC,oldbmp);
	delete []x;
	delete []y;
} 

void CSnake::InitDraw(HDC hDC)
{
	m_headbmp=LoadBitmap(hInst,MAKEINTRESOURCE(IDB_HEAD));
	m_nodebmp=LoadBitmap(hInst,MAKEINTRESOURCE(IDB_NODE));
	m_shrunken=LoadBitmap(hInst,MAKEINTRESOURCE(IDB_SHRUNKEN));
	m_bmDC=CreateCompatibleDC(hDC);
}	

bool CSnake::SetTrend(ETrend trend)
{
	if(NotTrend()==trend) return false;
	m_headTrend=trend;
	return true;
}

//�����ײ
bool CSnake::IsBump(CPanel *pPanel)
{
	//���ܱ߿�
/*	if(m_head->x<0 || m_head->x > pPanel->GetGx() ||
		m_head->y<0 || m_head->y > pPanel->GetGy())
	{
		return true;
	}
*/
	if(m_head->x<0)m_head->x=pPanel->GetGx();
	if(m_head->x>pPanel->GetGx())m_head->x=0;
	if(m_head->y<0)m_head->y=pPanel->GetGy();
	if(m_head->y>pPanel->GetGy())m_head->y=0;

	//�Լ�
	//ȡ�����нڵ�λ��
	int *x=new int[m_length+2];
	int *y=new int[m_length+2];
	GetSnack(x,y);
	//��ͷ������Ƚ�
	for(int i=1;i<=m_length+1;i++)
	{
		if(x[0]==x[i] && y[0]==y[i]) 
		{
			delete []x;
			delete []y;
			return true;
		}
	}
	delete []x;
	delete []y;
	return false;
}

bool CSnake::IsEatApple(CMill *pMill)
{
	POINT ApplePos=pMill->GetPos();
	if(m_head->x==ApplePos.x && m_head->y==ApplePos.y)
	{
		return true;
	}
	return false;
}
