package com.javaworld.awslambda.widget.handlers;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.javaworld.awslambda.widget.model.Widget;
import com.javaworld.awslambda.widget.model.WidgetRequest;

public class GetWidgetHandler implements RequestHandler<WidgetRequest, Widget> {
    @Override
    public Widget handleRequest(WidgetRequest widgetRequest, Context context) {
    	// Create a connection to DynamoDB
    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

    	// Build a mapper
    	DynamoDBMapper mapper = new DynamoDBMapper(client);
    	
    	context.getLogger().log("Request Id: " + widgetRequest.getId());
    	// Load the widget by id
    	Widget widget = mapper.load(Widget.class, widgetRequest.getId());
    	
    	if (widget == null) {
    		context.getLogger().log("No widget found for the id: "  + widgetRequest.getId());
    		return new Widget("100", "No widget found...");
    	}
    	
    	context.getLogger().log("Found Result in DB! " + widget.getId() + " : " + widget.getName());
    	
    	return widget;
    }
    
    
//    public Widget handleRequest(WidgetRequest widgetRequest, Context context) {
////      return new Widget(widgetRequest.getId(), "Senthil: My Widget " + widgetRequest.getId());
//  	
//  	// Create a connection to DynamoDB
//  	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
//  	DynamoDB dynamoDB = new DynamoDB(client);
//  	
//  	// Get a reference to Widget table
//  	Table table = dynamoDB.getTable("Widget");
//  	
//  	// Get our item by Id
//  	Item item = table.getItem("id", widgetRequest.getId());
//  	if (item != null) {
//  		System.out.println(item.toJSONPretty());
//  		
//  		// Return new Widget Object
//  		return new Widget(widgetRequest.getId(), item.getString("name"));
//  	} else {
//  		return new Widget();
//  	}
//  }
}
