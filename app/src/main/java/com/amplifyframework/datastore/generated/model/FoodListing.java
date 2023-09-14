package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
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

/** This is an auto generated class representing the FoodListing type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "FoodListings", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
})
public final class FoodListing implements Model {
  public static final QueryField ID = field("FoodListing", "id");
  public static final QueryField AVAILABLE_FOOD_ITEMS = field("FoodListing", "availableFoodItems");
  public static final QueryField IS_WILLING_TO_DELIVER = field("FoodListing", "isWillingToDeliver");
  public static final QueryField IS_AVAILABLE_FOR_PICKUP = field("FoodListing", "isAvailableForPickup");
  public static final QueryField CONTACT_EMAIL = field("FoodListing", "contactEmail");
  public static final QueryField USER_ID = field("FoodListing", "UserId");
  public static final QueryField ZIPCODE = field("FoodListing", "zipcode");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String availableFoodItems;
  private final @ModelField(targetType="Boolean") Boolean isWillingToDeliver;
  private final @ModelField(targetType="Boolean") Boolean isAvailableForPickup;
  private final @ModelField(targetType="String") String contactEmail;
  private final @ModelField(targetType="ID", isRequired = true) String UserId;
  private final @ModelField(targetType="String") String zipcode;
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
  
  public String getAvailableFoodItems() {
      return availableFoodItems;
  }
  
  public Boolean getIsWillingToDeliver() {
      return isWillingToDeliver;
  }
  
  public Boolean getIsAvailableForPickup() {
      return isAvailableForPickup;
  }
  
  public String getContactEmail() {
      return contactEmail;
  }
  
  public String getUserId() {
      return UserId;
  }
  
  public String getZipcode() {
      return zipcode;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private FoodListing(String id, String availableFoodItems, Boolean isWillingToDeliver, Boolean isAvailableForPickup, String contactEmail, String UserId, String zipcode) {
    this.id = id;
    this.availableFoodItems = availableFoodItems;
    this.isWillingToDeliver = isWillingToDeliver;
    this.isAvailableForPickup = isAvailableForPickup;
    this.contactEmail = contactEmail;
    this.UserId = UserId;
    this.zipcode = zipcode;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      FoodListing foodListing = (FoodListing) obj;
      return ObjectsCompat.equals(getId(), foodListing.getId()) &&
              ObjectsCompat.equals(getAvailableFoodItems(), foodListing.getAvailableFoodItems()) &&
              ObjectsCompat.equals(getIsWillingToDeliver(), foodListing.getIsWillingToDeliver()) &&
              ObjectsCompat.equals(getIsAvailableForPickup(), foodListing.getIsAvailableForPickup()) &&
              ObjectsCompat.equals(getContactEmail(), foodListing.getContactEmail()) &&
              ObjectsCompat.equals(getUserId(), foodListing.getUserId()) &&
              ObjectsCompat.equals(getZipcode(), foodListing.getZipcode()) &&
              ObjectsCompat.equals(getCreatedAt(), foodListing.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), foodListing.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getAvailableFoodItems())
      .append(getIsWillingToDeliver())
      .append(getIsAvailableForPickup())
      .append(getContactEmail())
      .append(getUserId())
      .append(getZipcode())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("FoodListing {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("availableFoodItems=" + String.valueOf(getAvailableFoodItems()) + ", ")
      .append("isWillingToDeliver=" + String.valueOf(getIsWillingToDeliver()) + ", ")
      .append("isAvailableForPickup=" + String.valueOf(getIsAvailableForPickup()) + ", ")
      .append("contactEmail=" + String.valueOf(getContactEmail()) + ", ")
      .append("UserId=" + String.valueOf(getUserId()) + ", ")
      .append("zipcode=" + String.valueOf(getZipcode()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static AvailableFoodItemsStep builder() {
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
  public static FoodListing justId(String id) {
    return new FoodListing(
      id,
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
      availableFoodItems,
      isWillingToDeliver,
      isAvailableForPickup,
      contactEmail,
      UserId,
      zipcode);
  }
  public interface AvailableFoodItemsStep {
    UserIdStep availableFoodItems(String availableFoodItems);
  }
  

  public interface UserIdStep {
    BuildStep userId(String userId);
  }
  

  public interface BuildStep {
    FoodListing build();
    BuildStep id(String id);
    BuildStep isWillingToDeliver(Boolean isWillingToDeliver);
    BuildStep isAvailableForPickup(Boolean isAvailableForPickup);
    BuildStep contactEmail(String contactEmail);
    BuildStep zipcode(String zipcode);
  }
  

  public static class Builder implements AvailableFoodItemsStep, UserIdStep, BuildStep {
    private String id;
    private String availableFoodItems;
    private String UserId;
    private Boolean isWillingToDeliver;
    private Boolean isAvailableForPickup;
    private String contactEmail;
    private String zipcode;
    @Override
     public FoodListing build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new FoodListing(
          id,
          availableFoodItems,
          isWillingToDeliver,
          isAvailableForPickup,
          contactEmail,
          UserId,
          zipcode);
    }
    
    @Override
     public UserIdStep availableFoodItems(String availableFoodItems) {
        Objects.requireNonNull(availableFoodItems);
        this.availableFoodItems = availableFoodItems;
        return this;
    }
    
    @Override
     public BuildStep userId(String userId) {
        Objects.requireNonNull(userId);
        this.UserId = userId;
        return this;
    }
    
    @Override
     public BuildStep isWillingToDeliver(Boolean isWillingToDeliver) {
        this.isWillingToDeliver = isWillingToDeliver;
        return this;
    }
    
    @Override
     public BuildStep isAvailableForPickup(Boolean isAvailableForPickup) {
        this.isAvailableForPickup = isAvailableForPickup;
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
    private CopyOfBuilder(String id, String availableFoodItems, Boolean isWillingToDeliver, Boolean isAvailableForPickup, String contactEmail, String userId, String zipcode) {
      super.id(id);
      super.availableFoodItems(availableFoodItems)
        .userId(userId)
        .isWillingToDeliver(isWillingToDeliver)
        .isAvailableForPickup(isAvailableForPickup)
        .contactEmail(contactEmail)
        .zipcode(zipcode);
    }
    
    @Override
     public CopyOfBuilder availableFoodItems(String availableFoodItems) {
      return (CopyOfBuilder) super.availableFoodItems(availableFoodItems);
    }
    
    @Override
     public CopyOfBuilder userId(String userId) {
      return (CopyOfBuilder) super.userId(userId);
    }
    
    @Override
     public CopyOfBuilder isWillingToDeliver(Boolean isWillingToDeliver) {
      return (CopyOfBuilder) super.isWillingToDeliver(isWillingToDeliver);
    }
    
    @Override
     public CopyOfBuilder isAvailableForPickup(Boolean isAvailableForPickup) {
      return (CopyOfBuilder) super.isAvailableForPickup(isAvailableForPickup);
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
  

  public static class FoodListingIdentifier extends ModelIdentifier<FoodListing> {
    private static final long serialVersionUID = 1L;
    public FoodListingIdentifier(String id) {
      super(id);
    }
  }
  
}
