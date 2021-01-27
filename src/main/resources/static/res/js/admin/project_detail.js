/**
 *  添加扩展信息到显示表格
 * @param obj
 */
function addExtend(obj) {
	var tr_ = '<tr>',_tr = '</tr>';
	var html = tr_ + '<td>' + obj.fieldName + '</td>'
		+ '<td>' + obj.fieldNameKey + '</td>'
		+ '<td>' + obj.fieldType + '</td>'
		+ '<td style="text-align: center;" value="'+obj.fieldFlag+'">' + (obj.fieldFlag?'<img src="images/tick_16x16.png"/>':'') + '</td>'
		+ '<td style="text-align: center;" value="'+obj.groupFlag+'">' + (obj.groupFlag?'<img src="images/tick_16x16.png"/>':'') + '</td>'
		+ '<td style="text-align: center;">' + obj.groupData + '</td>'
		+ '<td style="text-align: center;">' + obj.groupWeight + '</td>'
		+ '<td style="text-align: center;" class="td_right"><img style="cursor: pointer;" src="images/deletered.png"/></td>';
	$('#expend_show_list').append(html);
	$('#expend_show_list:last-child .td_right img').on('click',function(){
		$(this).parent().parent().remove();
		$('textarea[name="model.proMemo"]').val($.toJSON(getAllExtend()));		
	});
	$('textarea[name="model.proMemo"]').val($.toJSON(getAllExtend()));
}

/**
 *  获取所有的扩展信息
 */
function getAllExtend() {
	var table = $('#expend_show_list'),
		data = [];
	table.find('tr').each(function(i,n){
		if(i > 0) {
			var tds = $(n).find('td').toArray();
			var obj = {
				fieldName:$(tds[0]).html()||'',
				fieldNameKey:$(tds[1]).html()||'',
				fieldType:$(tds[2]).html()||'',
				fieldFlag:$(tds[3]).attr('value')||'',
				groupFlag:$(tds[4]).attr('value')||'',
				groupData:$(tds[5]).html()||'',
				groupWeight:$(tds[6]).html()||''
			};
			data[i-1] = obj;
		}
	});
	return data;
}

function initExtend() {
	var str = $('textarea[name="model.proMemo"]').val();
	if(str) {
		var json = $.evalJSON(str);
		if(json && json.length > 0) {
			for(var i = 0; i < json.length; i++) {
				addExtend(json[i]);
			}
		}
	}
}

$(function(){
    $('#save_project').validationEngine('attach', {
        scroll: true
    });
    // 删除校验信息
    $('#save_project').validationEngine('hide');
	// ------------- init groupmethod
	var groupMethodSE = $('select[name="model.proGroupMethod"]');
	var groupValue = $('#groupMethodHidden').val() || 1;
	groupValue = groupValue*1;
	var groupMethodF = APP_CONFIG.GroupMethodEnum;
	for(var i =0; i < groupMethodF.length;i++) {
		if(groupValue == groupMethodF[i][0]) {
			groupMethodSE.append(
					'<option value="' + groupMethodF[i][0] + '" selected>' + groupMethodF[i][1]
							+ '</option>', null);			
		} else {
			groupMethodSE.append(
					'<option value="' + groupMethodF[i][0] + '">' + groupMethodF[i][1]
					+ '</option>', null);
		}
	}
	// ------------- init groupmethod end

	$('#save_extend').on('click',function(){
		var key = $('#_addExtend input[name="field_name"]').val();
		if(!key) {
			return;
		}
		var value = $('#_addExtend input[name="field_name_key"]').val();
		if(!value) {
			return;
		}
		addExtend({
			fieldName:key,
			fieldNameKey:value,
			fieldType:$('#_addExtend select[name="field_type"]').val(),
			groupData:$('#_addExtend input[name="group_data"]').val()||'',
			fieldFlag:$('input[name="field_flag"]:checked').val() || '',
			groupFlag:$('input[name="group_flag"]:checked').val() || '',
			groupWeight:$('#_addExtend input[name="group_weight"]').val() || ''
		});
        $('#_addExtend').modal('hide');
        $('#_addExtend :input').val([]);
        $('#_addExtend :input[name="group_weight"]').val(1.0);
	});
	// $('#save_project').validationEngine({doNotShowAllErrosOnSubmit:true});
	
	$('input[name="model.proGroupCount"]').val($('input[name="model.proGroupCount"]').val() || 2);
	
	initExtend();
	
});