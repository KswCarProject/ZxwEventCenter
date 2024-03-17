package com.szchoiceway.eventcenter.listener;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.szchoiceway.eventcenter.EventService;
import com.szchoiceway.eventcenter.R;
import com.szchoiceway.eventcenter.SysProviderOpt;
import com.szchoiceway.eventcenter.lexus.LexusAirViewComponent;

public class OnLexusAirEventListener implements View.OnClickListener, View.OnTouchListener {
    public boolean isTouching = false;
    private Context mContext;

    public OnLexusAirEventListener(Context context) {
        this.mContext = context;
    }

    public void onClick(View view) {
        LexusAirViewComponent.ButtonState buttonState = (LexusAirViewComponent.ButtonState) view.getTag();
        int i = 0;
        int i2 = buttonState.isDualChecked ? 1 : 0;
        if (buttonState.isAcChecked) {
            i2 |= 16;
        }
        if (buttonState.isCycleChecked) {
            i2 |= 8;
        }
        if (buttonState.isFrontChecked) {
            i2 |= 4;
        }
        if (buttonState.isRearChecked) {
            i2 |= 2;
        }
        byte[] bytes = AirCmdUtil.getBytes(i2);
        if (buttonState.isAutoChecked) {
            i = AirCmdUtil.AUTO;
        }
        sendCmd(AirCmdUtil.getBytes(bytes, i));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.isTouching = true;
            view.setPressed(true);
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.isTouching = false;
            view.setPressed(false);
        }
        switch (view.getId()) {
            case R.id.air_left_win_down_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getLeftTempDownBytes());
                    break;
                }
            case R.id.air_left_win_up_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getLeftTempUpBytes());
                    break;
                }
            case R.id.air_right_win_down_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getRightTempDownBytes());
                    break;
                }
            case R.id.air_right_win_up_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getRightTempUpBytes());
                    break;
                }
            case R.id.air_switch_ac_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getAcBytes());
                    break;
                }
            case R.id.air_switch_auto_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getAutoBytes());
                    break;
                }
            case R.id.air_switch_cycle_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getCircleBytes());
                    break;
                }
            case R.id.air_switch_dual_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getDualBytes());
                    break;
                }
            case R.id.air_switch_front_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getFrontWindBytes());
                    break;
                }
            case R.id.air_switch_rear_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getRearWindBytes());
                    break;
                }
            case R.id.air_win_speed_down_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getWinDownBytes());
                    break;
                }
            case R.id.air_win_speed_up_btn:
                if (motionEvent.getAction() != 0) {
                    if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
                        sendCmd(AirCmdUtil.getReleaseBytes());
                        break;
                    }
                } else {
                    sendCmd(AirCmdUtil.getWinUpBytes());
                    break;
                }
        }
        return true;
    }

    private void sendCmd(byte[] bArr) {
        SysProviderOpt.getInstance(this.mContext).getRecordBoolean(SysProviderOpt.KSW_LEXUS_AIR_CONTROL, false);
        EventService.getInstance().getSendThread().notifyToSend(bArr);
    }
}
