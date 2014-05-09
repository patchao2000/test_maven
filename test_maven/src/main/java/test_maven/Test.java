package test_maven;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;

import junit.framework.TestCase;

public class Test extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void test1() {
        //���������ļ�
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        repositoryService.createDeployment()
            .addClasspathResource("Interview.bpmn")
            .deploy();
        String processId = runtimeService.startProcessInstanceByKey("Interview").getId();
        
        TaskService taskService = processEngine.getTaskService();
        HistoryService historyService = processEngine.getHistoryService();
        //�õ����Ե�����
        System.out.println("***************�������̿�ʼ***************");
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
        for (Task task : tasks) {
            System.out.println("������Դ��������name:"+task.getName()+",id:"+task.getId());
            taskService.claim(task.getId(), "����");

        }
        System.out.println("����������������"+taskService.createTaskQuery().taskAssignee("����").count());
        tasks = taskService.createTaskQuery().taskAssignee("����").list();
        for (Task task : tasks) {
            System.out.println("����������name:"+task.getName()+",id:"+task.getId());
            taskService.complete(task.getId());
        }
        System.out.println("����������������"+taskService.createTaskQuery().taskAssignee("����").count());
        System.out.println("***************�������̽���***************");
        
        System.out.println("***************һ�����̿�ʼ***************");
        tasks = taskService.createTaskQuery().taskCandidateGroup("������").list();
        for (Task task : tasks) {
            System.out.println("������������name:"+task.getName()+",id:"+task.getId());
            taskService.claim(task.getId(), "����");
        }
        System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
        for (Task task : tasks) {
            System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
            taskService.complete(task.getId());
        }
        System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
        System.out.println("***************һ�����̽���***************");
        
        System.out.println("***************�������̿�ʼ***************");
        tasks = taskService.createTaskQuery().taskCandidateGroup("������").list();
        for (Task task : tasks) {
            System.out.println("������������name:"+task.getName()+",id:"+task.getId());
            taskService.claim(task.getId(), "����");
        }
        System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
        for (Task task : tasks) {
            System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
            taskService.complete(task.getId());
        }
        System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
        System.out.println("***************�������̽���***************");
        
        System.out.println("***************HR�����̿�ʼ***************");
        tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
        for (Task task : tasks) {
            System.out.println("������������name:"+task.getName()+",id:"+task.getId());
            taskService.claim(task.getId(), "����");
        }
        System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
        for (Task task : tasks) {
            System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
            taskService.complete(task.getId());
        }
        System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
        System.out.println("***************HR�����̽���***************");
        
        System.out.println("***************¼�����̿�ʼ***************");
        tasks = taskService.createTaskQuery().taskCandidateGroup("������Դ��").list();
        for (Task task : tasks) {
            System.out.println("������������name:"+task.getName()+",id:"+task.getId());
            taskService.claim(task.getId(), "����");
        }
        System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
        for (Task task : tasks) {
            System.out.println("���ĵ�����name:"+task.getName()+",id:"+task.getId());
            taskService.complete(task.getId());
        }
        System.out.println("���ĵ�����������"+taskService.createTaskQuery().taskAssignee("����").count());
        System.out.println("***************¼�����̽���***************");
        
        
        HistoricProcessInstance historicProcessInstance = historyService
                .createHistoricProcessInstanceQuery()
                .processInstanceId(processId).singleResult();
        System.out.println("���̽���ʱ�䣺"+historicProcessInstance.getEndTime());
	}

	public void test2() {
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
		// Create Activiti process engine
//		ProcessEngine processEngine = ProcessEngineConfiguration
//		    .createStandaloneProcessEngineConfiguration()
//		    .buildProcessEngine();

		// Get Activiti services
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();

		// Deploy the process definition
		repositoryService.createDeployment()
			.addClasspathResource("FinancialReportProcess.bpmn")
			.deploy();

		// Start a process instance
	    String procId = runtimeService.startProcessInstanceByKey("financialReport").getId();

		// Get the first task
	    TaskService taskService = processEngine.getTaskService();
	    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
	    for (Task task : tasks) {
	      System.out.println("Following task is available for accountancy group: " + task.getName());

	      // claim it
	      taskService.claim(task.getId(), "fozzie");
	    }

	    // Verify Fozzie can now retrieve the task
	    tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
	    for (Task task : tasks) {
	      System.out.println("Task for fozzie: " + task.getName());

	      // Complete the task
	      taskService.complete(task.getId());
	    }

	    System.out.println("Number of tasks for fozzie: "
	            + taskService.createTaskQuery().taskAssignee("fozzie").count());

	    // Retrieve and claim the second task
	    tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
	    for (Task task : tasks) {
	      System.out.println("Following task is available for accountancy group: " + task.getName());
	      taskService.claim(task.getId(), "kermit");
	    }

	    // Completing the second task ends the process
	    for (Task task : tasks) {
	      taskService.complete(task.getId());
	    }

	    // verify that the process is actually finished
	    HistoryService historyService = processEngine.getHistoryService();
	    HistoricProcessInstance historicProcessInstance =
	      historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
	    System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
	}
}
