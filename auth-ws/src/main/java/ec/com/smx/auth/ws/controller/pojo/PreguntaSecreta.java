package ec.com.smx.auth.ws.controller.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

public class PreguntaSecreta
{
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String systemId;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Long questionId;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String answer;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String question;
  
  public PreguntaSecreta(String systemId, Long questionId, String answer, String question)
  {
    this.systemId = systemId;
    this.questionId = questionId;
    this.answer = answer;
    this.question = question;
  }
  
  public String getSystemId()
  {
    return this.systemId;
  }
  
  public void setSystemId(String systemId)
  {
    this.systemId = systemId;
  }
  
  public String getAnswer()
  {
    return this.answer;
  }
  
  public void setAnswer(String answer)
  {
    this.answer = answer;
  }
  
  public String getQuestion()
  {
    return this.question;
  }
  
  public void setQuestion(String question)
  {
    this.question = question;
  }
  
  public Long getQuestionId()
  {
    return this.questionId;
  }
  
  public void setQuestionId(Long questionId)
  {
    this.questionId = questionId;
  }
}