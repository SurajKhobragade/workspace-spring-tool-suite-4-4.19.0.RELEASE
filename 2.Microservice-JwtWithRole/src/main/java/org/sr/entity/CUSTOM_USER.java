package org.sr.entity;

import java.io.Serializable;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Document(collection = "user")
@Data
@RequiredArgsConstructor
public class CUSTOM_USER implements Serializable {
	@Id
	private String id;

	@NonNull
	private String username;

	@NonNull
	private String password;

	@NonNull
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

}
