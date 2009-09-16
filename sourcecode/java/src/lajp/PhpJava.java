//-----------------------------------------------------------
// LAJP-java (2009-09 http://code.google.com/p/lajp/)
// 
// Version: 9.09.01
// License: http://www.apache.org/licenses/LICENSE-2.0
//-----------------------------------------------------------

package lajp;

/**
 * LAJP主线程
 * @author diaoyf
 *
 */
public class PhpJava
{
	/** 消息队列KEY */
	static final int MSGQ_KEY = 0x20021230;

	/** 握手消息类型 */
	final static int HANDSHAKE_TYPE = 1; 
	/** 消息最大字节数 */
	final static int PHPJAVA_MSG_MAX = 4096;
	
	/** 主消息队列id */
	static int msqid;
	
	static
	{
		//加载JNI
		System.loadLibrary("lajpmsgq");
	}

	public static void main(String[] args)
	{
		//初始化System V IPC
		initIPC();
		
		//获得消息队列id
		msqid = MsgQ.msgget(MSGQ_KEY);
		//接收buffer
		byte[] buffer = new byte[1024];
		//接收信息长度
		int bufLen = 1024;
		
		while (true)
		{
			//请求进程消息类型id
			int processId = -1;
			//解析请求类型
			byte type = 0x00;

			try
			{
				//接收握手信息
				bufLen = MsgQ.msgrcv(msqid, buffer, 1024, HANDSHAKE_TYPE);								
			}
			catch (Throwable e)
			{
				System.out.println("[LAJP Exception(warn)]");
				e.printStackTrace();
				continue;
			}
			
			//消息长度检查(握手消息长度35字节)
			if (bufLen != 35)
			{
				System.out.println("[LAJP Error(warn)]:HandShake Message length != 35");
				continue;
			}
			
//			//--
//			for (int i = 0; i < bufLen; i++)
//			{
//				System.out.printf("[%d]0x%x,", i, buffer[i]);
//			}
//			System.out.println();
//			System.out.println("handShake:" + new String(buffer, 0, bufLen));

			
			//解析消息-----------------------------
			type = buffer[14];
			processId = Integer.parseInt(new String(buffer, 23, 10));

//			//--
			System.out.printf("processId:%d\n",processId);
			
			if (type == 0x73) //0x73: "s"
			{
				try
				{
					new SingleThread(processId).start();
				}
				catch (Throwable e)
				{
					System.out.println("[LAJP Exception(warn)] SingleThread Exception: ");
					e.printStackTrace();
					continue;
				}
			}
			else if (type == 0x6d) //0x6d: "m"
			{
				//TODO 连续请求
			}
			
		}//循环结束

	}
	
	/**
	 * 初始化IPC
	 */
	public static void initIPC()
	{
		System.out.println("init IPC...");
		
		//先删除sem
		int semid = MsgQ.semget(MSGQ_KEY);
		if (semid == -1)
		{
			System.out.printf("semget(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}
		if (MsgQ.semclose(semid) == -1)
		{
			System.out.printf("semctl(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}
		
		//重建msg
		int msgid = MsgQ.msgget(MSGQ_KEY);
		if (msgid == -1)
		{
			System.out.printf("msgget(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}
		if (MsgQ.msgclose(msgid) == -1)
		{
			System.out.printf("msgctl(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}
		if (MsgQ.msgget(MSGQ_KEY) == -1)
		{
			System.out.printf("msgget(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}

		System.out.println("init [IPC] Message Queue OK...");

		//重建shm
		int shmid = MsgQ.shmget(MSGQ_KEY, 10);
		if (shmid == -1)
		{
			System.out.printf("shmget(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}
		if (MsgQ.shmclose(shmid) == -1)
		{
			System.out.printf("shmctl(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}
		if (MsgQ.shmget(MSGQ_KEY, 10) == -1)
		{
			System.out.printf("shmctl(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}
		
		System.out.println("init [IPC] Shared Memory OK...");

		//创建sem
		if (MsgQ.semget(MSGQ_KEY) == -1)
		{
			System.out.printf("semget(0x%x) error, can't start LAJP-JAVA.\n", MSGQ_KEY);
			System.exit(-1);
		}

		System.out.println("init [IPC] Semaphore OK...");
	}

}
