package com.vladislavskiy.customer;

public record CustomerRegistationRequest(Integer id, String firstName, String lastName,String email)
{
}
