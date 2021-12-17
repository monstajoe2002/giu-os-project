import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import scheduler.*;

class CPUSchedulerTest {

	@Test
	void FCFS_Test1() {
		Assertions.assertEquals("A(3),B(6),C(4),D(5),E(2)",
				CPUScheduler.Scheduler_FCFS("A,B,C,D,E;0,2,4,5,8;3,6,4,5,2"));
	}

	@Test
	void SJF_Test1() {
		Assertions.assertEquals("A(3),B(6),E(2),C(4),D(5)",
				CPUScheduler.Scheduler_SJF("A,B,C,D,E;0,2,4,5,8;3,6,4,5,2"));
	}

	@Test
	void RR_Test1() {
		Assertions.assertEquals("A(2),B(2),A(1),C(2),B(2),D(2),C(2),E(2),B(2),D(3)",
				CPUScheduler.Scheduler_RR("A,B,C,D,E;0,2,4,5,8;3,6,4,5,2"));

	}

}
