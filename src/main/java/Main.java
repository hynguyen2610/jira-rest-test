
import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.atlassian.util.concurrent.Promise;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {

  public static void main(String[] args) throws URISyntaxException {
    URI jiraServerUri = new URI("http://localhost:8080/rest/api/2/issue");
    JiraRestClient restClient = null;
    final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
    restClient = factory.createWithBasicHttpAuthentication(jiraServerUri, "Test", "12345");
    final IssueRestClient client = restClient.getIssueClient();
    IssueInputBuilder issueBuilder = new IssueInputBuilder("PU", 1L, "Testing the ISSUES");
    issueBuilder.setDescription("issue ssss description");
    IssueInput issueInput = issueBuilder.build();
    Promise<BasicIssue> promiseIssue = client.createIssue(issueInput);
    BasicIssue issue = promiseIssue.claim();
    System.out.println("Issue: " + issue.toString());

  }
}
