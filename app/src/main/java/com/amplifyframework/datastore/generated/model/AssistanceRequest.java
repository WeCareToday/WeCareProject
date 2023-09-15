package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the AssistanceRequest type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "AssistanceRequests", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class AssistanceRequest implements Model {
  public static final QueryField ID = field("AssistanceRequest", "id");
  public static final QueryField TITLE = field("AssistanceRequest", "title");
  public static final QueryField DESCRIPTION = field("AssistanceRequest", "description");
  public static final QueryField IS_FOR_ORGANIZATION = field("AssistanceRequest", "isForOrganization");
  public static final QueryField IS_WILLING_TO_MEET = field("AssistanceRequest", "isWillingToMeet");
  public static final QueryField FAMILY_SIZE = field("AssistanceRequest", "familySize");
  public static final QueryField DIET_RESTRICTIONS = field("AssistanceRequest", "dietRestrictions");
  public static final QueryField NEED_DATE = field("AssistanceRequest", "needDate");
  public static final QueryField IS_REQUEST_ANONYMOUS = field("AssistanceRequest", "isRequestAnonymous");
  public static final QueryField IS_NEED_SATISFIED = field("AssistanceRequest", "isNeedSatisfied");
  public static final QueryField USER_ID = field("AssistanceRequest", "userID");
  public static final QueryField CONTACT_EMAIL = field("AssistanceRequest", "contactEmail");
  public static final QueryField ZIPCODE = field("AssistanceRequest", "zipcode");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String title;
  private final @ModelField(targetType="String", isRequired = true) String description;
  private final @ModelField(targetType="Boolean") Boolean isForOrganization;
  private final @ModelField(targetType="Boolean") Boolean isWillingToMeet;
  private final @ModelField(targetType="Int") Integer familySize;
  private final @ModelField(targetType="String") String dietRestrictions;
  private final @ModelField(targetType="AWSDateTime", isRequired = true) Temporal.DateTime needDate;
  private final @ModelField(targetType="Boolean") Boolean isRequestAnonymous;
  private final @ModelField(targetType="Boolean") Boolean isNeedSatisfied;
  private final @ModelField(targetType="ID", isRequired = true) String userID;
  private final @ModelField(targetType="String") String contactEmail;
  private final @ModelField(targetType="String") String zipcode;
  private final @ModelField(targetType="FoodListing") @HasMany(associatedWith = "assistanceRequest", type = FoodListing.class) List<FoodListing> donations = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getTitle() {
      return title;
  }
  
  public String getDescription() {
      return description;
  }
  
  public Boolean getIsForOrganization() {
      return isForOrganization;
  }
  
  public Boolean getIsWillingToMeet() {
      return isWillingToMeet;
  }
  
  public Integer getFamilySize() {
      return familySize;
  }
  
  public String getDietRestrictions() {
      return dietRestrictions;
  }
  
  public Temporal.DateTime getNeedDate() {
      return needDate;
  }
  
  public Boolean getIsRequestAnonymous() {
      return isRequestAnonymous;
  }
  
  public Boolean getIsNeedSatisfied() {
      return isNeedSatisfied;
  }
  
  public String getUserId() {
      return userID;
  }
  
  public String getContactEmail() {
      return contactEmail;
  }
  
  public String getZipcode() {
      return zipcode;
  }
  
  public List<FoodListing> getDonations() {
      return donations;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private AssistanceRequest(String id, String title, String description, Boolean isForOrganization, Boolean isWillingToMeet, Integer familySize, String dietRestrictions, Temporal.DateTime needDate, Boolean isRequestAnonymous, Boolean isNeedSatisfied, String userID, String contactEmail, String zipcode) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.isForOrganization = isForOrganization;
    this.isWillingToMeet = isWillingToMeet;
    this.familySize = familySize;
    this.dietRestrictions = dietRestrictions;
    this.needDate = needDate;
    this.isRequestAnonymous = isRequestAnonymous;
    this.isNeedSatisfied = isNeedSatisfied;
    this.userID = userID;
    this.contactEmail = contactEmail;
    this.zipcode = zipcode;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      AssistanceRequest assistanceRequest = (AssistanceRequest) obj;
      return ObjectsCompat.equals(getId(), assistanceRequest.getId()) &&
              ObjectsCompat.equals(getTitle(), assistanceRequest.getTitle()) &&
              ObjectsCompat.equals(getDescription(), assistanceRequest.getDescription()) &&
              ObjectsCompat.equals(getIsForOrganization(), assistanceRequest.getIsForOrganization()) &&
              ObjectsCompat.equals(getIsWillingToMeet(), assistanceRequest.getIsWillingToMeet()) &&
              ObjectsCompat.equals(getFamilySize(), assistanceRequest.getFamilySize()) &&
              ObjectsCompat.equals(getDietRestrictions(), assistanceRequest.getDietRestrictions()) &&
              ObjectsCompat.equals(getNeedDate(), assistanceRequest.getNeedDate()) &&
              ObjectsCompat.equals(getIsRequestAnonymous(), assistanceRequest.getIsRequestAnonymous()) &&
              ObjectsCompat.equals(getIsNeedSatisfied(), assistanceRequest.getIsNeedSatisfied()) &&
              ObjectsCompat.equals(getUserId(), assistanceRequest.getUserId()) &&
              ObjectsCompat.equals(getContactEmail(), assistanceRequest.getContactEmail()) &&
              ObjectsCompat.equals(getZipcode(), assistanceRequest.getZipcode()) &&
              ObjectsCompat.equals(getCreatedAt(), assistanceRequest.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), assistanceRequest.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getTitle())
      .append(getDescription())
      .append(getIsForOrganization())
      .append(getIsWillingToMeet())
      .append(getFamilySize())
      .append(getDietRestrictions())
      .append(getNeedDate())
      .append(getIsRequestAnonymous())
      .append(getIsNeedSatisfied())
      .append(getUserId())
      .append(getContactEmail())
      .append(getZipcode())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("AssistanceRequest {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("title=" + String.valueOf(getTitle()) + ", ")
      .append("description=" + String.valueOf(getDescription()) + ", ")
      .append("isForOrganization=" + String.valueOf(getIsForOrganization()) + ", ")
      .append("isWillingToMeet=" + String.valueOf(getIsWillingToMeet()) + ", ")
      .append("familySize=" + String.valueOf(getFamilySize()) + ", ")
      .append("dietRestrictions=" + String.valueOf(getDietRestrictions()) + ", ")
      .append("needDate=" + String.valueOf(getNeedDate()) + ", ")
      .append("isRequestAnonymous=" + String.valueOf(getIsRequestAnonymous()) + ", ")
      .append("isNeedSatisfied=" + String.valueOf(getIsNeedSatisfied()) + ", ")
      .append("userID=" + String.valueOf(getUserId()) + ", ")
      .append("contactEmail=" + String.valueOf(getContactEmail()) + ", ")
      .append("zipcode=" + String.valueOf(getZipcode()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static TitleStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static AssistanceRequest justId(String id) {
    return new AssistanceRequest(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      title,
      description,
      isForOrganization,
      isWillingToMeet,
      familySize,
      dietRestrictions,
      needDate,
      isRequestAnonymous,
      isNeedSatisfied,
      userID,
      contactEmail,
      zipcode);
  }
  public interface TitleStep {
    DescriptionStep title(String title);
  }
  

  public interface DescriptionStep {
    NeedDateStep description(String description);
  }
  

  public interface NeedDateStep {
    UserIdStep needDate(Temporal.DateTime needDate);
  }
  

  public interface UserIdStep {
    BuildStep userId(String userId);
  }
  

  public interface BuildStep {
    AssistanceRequest build();
    BuildStep id(String id);
    BuildStep isForOrganization(Boolean isForOrganization);
    BuildStep isWillingToMeet(Boolean isWillingToMeet);
    BuildStep familySize(Integer familySize);
    BuildStep dietRestrictions(String dietRestrictions);
    BuildStep isRequestAnonymous(Boolean isRequestAnonymous);
    BuildStep isNeedSatisfied(Boolean isNeedSatisfied);
    BuildStep contactEmail(String contactEmail);
    BuildStep zipcode(String zipcode);
  }
  

  public static class Builder implements TitleStep, DescriptionStep, NeedDateStep, UserIdStep, BuildStep {
    private String id;
    private String title;
    private String description;
    private Temporal.DateTime needDate;
    private String userID;
    private Boolean isForOrganization;
    private Boolean isWillingToMeet;
    private Integer familySize;
    private String dietRestrictions;
    private Boolean isRequestAnonymous;
    private Boolean isNeedSatisfied;
    private String contactEmail;
    private String zipcode;
    @Override
     public AssistanceRequest build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new AssistanceRequest(
          id,
          title,
          description,
          isForOrganization,
          isWillingToMeet,
          familySize,
          dietRestrictions,
          needDate,
          isRequestAnonymous,
          isNeedSatisfied,
          userID,
          contactEmail,
          zipcode);
    }
    
    @Override
     public DescriptionStep title(String title) {
        Objects.requireNonNull(title);
        this.title = title;
        return this;
    }
    
    @Override
     public NeedDateStep description(String description) {
        Objects.requireNonNull(description);
        this.description = description;
        return this;
    }
    
    @Override
     public UserIdStep needDate(Temporal.DateTime needDate) {
        Objects.requireNonNull(needDate);
        this.needDate = needDate;
        return this;
    }
    
    @Override
     public BuildStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.userID = userId;
        return this;
    }
    
    @Override
     public BuildStep isForOrganization(Boolean isForOrganization) {
        this.isForOrganization = isForOrganization;
        return this;
    }
    
    @Override
     public BuildStep isWillingToMeet(Boolean isWillingToMeet) {
        this.isWillingToMeet = isWillingToMeet;
        return this;
    }
    
    @Override
     public BuildStep familySize(Integer familySize) {
        this.familySize = familySize;
        return this;
    }
    
    @Override
     public BuildStep dietRestrictions(String dietRestrictions) {
        this.dietRestrictions = dietRestrictions;
        return this;
    }
    
    @Override
     public BuildStep isRequestAnonymous(Boolean isRequestAnonymous) {
        this.isRequestAnonymous = isRequestAnonymous;
        return this;
    }
    
    @Override
     public BuildStep isNeedSatisfied(Boolean isNeedSatisfied) {
        this.isNeedSatisfied = isNeedSatisfied;
        return this;
    }
    
    @Override
     public BuildStep contactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }
    
    @Override
     public BuildStep zipcode(String zipcode) {
        this.zipcode = zipcode;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String title, String description, Boolean isForOrganization, Boolean isWillingToMeet, Integer familySize, String dietRestrictions, Temporal.DateTime needDate, Boolean isRequestAnonymous, Boolean isNeedSatisfied, String userId, String contactEmail, String zipcode) {
      super.id(id);
      super.title(title)
        .description(description)
        .needDate(needDate)
        .userId(userId)
        .isForOrganization(isForOrganization)
        .isWillingToMeet(isWillingToMeet)
        .familySize(familySize)
        .dietRestrictions(dietRestrictions)
        .isRequestAnonymous(isRequestAnonymous)
        .isNeedSatisfied(isNeedSatisfied)
        .contactEmail(contactEmail)
        .zipcode(zipcode);
    }
    
    @Override
     public CopyOfBuilder title(String title) {
      return (CopyOfBuilder) super.title(title);
    }
    
    @Override
     public CopyOfBuilder description(String description) {
      return (CopyOfBuilder) super.description(description);
    }
    
    @Override
     public CopyOfBuilder needDate(Temporal.DateTime needDate) {
      return (CopyOfBuilder) super.needDate(needDate);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder isForOrganization(Boolean isForOrganization) {
      return (CopyOfBuilder) super.isForOrganization(isForOrganization);
    }
    
    @Override
     public CopyOfBuilder isWillingToMeet(Boolean isWillingToMeet) {
      return (CopyOfBuilder) super.isWillingToMeet(isWillingToMeet);
    }
    
    @Override
     public CopyOfBuilder familySize(Integer familySize) {
      return (CopyOfBuilder) super.familySize(familySize);
    }
    
    @Override
     public CopyOfBuilder dietRestrictions(String dietRestrictions) {
      return (CopyOfBuilder) super.dietRestrictions(dietRestrictions);
    }
    
    @Override
     public CopyOfBuilder isRequestAnonymous(Boolean isRequestAnonymous) {
      return (CopyOfBuilder) super.isRequestAnonymous(isRequestAnonymous);
    }
    
    @Override
     public CopyOfBuilder isNeedSatisfied(Boolean isNeedSatisfied) {
      return (CopyOfBuilder) super.isNeedSatisfied(isNeedSatisfied);
    }
    
    @Override
     public CopyOfBuilder contactEmail(String contactEmail) {
      return (CopyOfBuilder) super.contactEmail(contactEmail);
    }
    
    @Override
     public CopyOfBuilder zipcode(String zipcode) {
      return (CopyOfBuilder) super.zipcode(zipcode);
    }
  }
  

  public static class AssistanceRequestIdentifier extends ModelIdentifier<AssistanceRequest> {
    private static final long serialVersionUID = 1L;
    public AssistanceRequestIdentifier(String id) {
      super(id);
    }
  }
  
}
